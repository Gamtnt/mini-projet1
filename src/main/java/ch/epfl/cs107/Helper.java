package ch.epfl.cs107;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static java.util.Objects.nonNull;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import ch.epfl.cs107.utils.Text;

/**
 * Helper class. This class contains all the methods considered to be useful
 * and which cannot be implemented by the students. (Outside the scope of the course).
 * Most of these methods can be implemented by the students after the CS-108 course
 * next semester.
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public final class Helper {

    /** Project's resource folder - Follows Maven convention */
    private static final Path res_folder = Path.of("src", "main", "resources");

    /** Pseudo random generator */
    private static final Random rand = new Random();

    /*
     * Hidden executed code - Create the resource folder if missing
     */
    static {
        if (Files.exists(res_folder))
            if (!Files.isDirectory(res_folder))
                fail("File %s is not a directory.", res_folder);
            else try {
                Files.createDirectories(res_folder);
            } catch (IOException e){
                fail("Cannot create directory '%s'", res_folder);
            }
    }

    // DO NOT CHANGE THIS, MORE ON THAT ON WEEK 7
    private Helper(){}

    // ============================================================================================
    // =================================== RANDOM GENERATION ======================================
    // ============================================================================================

    /**
     * Generate a random byte array of a given length
     * @param length the size of the generated array
     * @return random bytes in an array of length '{@code length}'
     */
    public static byte[] generateRandomBytes(int length) {
        assert length >= 0;
        final var result = new byte[length];
        rand.nextBytes(result);
        return result;
    }

    // ============================================================================================
    // ============================= BINARY FILE MANIPULATION METHODS =============================
    // ============================================================================================

    /**
     * Read an <b>UTF-8</b> encoded text file from the filesystem
     * @param path Path to the desired file
     * @return Content of the file
     * @apiNote The provided path will be considered relative to the resource folder if not absolute
     */
    public static byte[] read(String path) {
        assert nonNull(path);
        try {
            final var content = Files.readAllBytes(res_folder.resolve(path));
            return content;
        } catch (IOException e){
            return fail("An error occurred while trying to read from : '%s'", path);
        }
    }

    /**
     * Write an <b>UTF-8</b> encoded String to the disk.
     * @param path Path to the desired file
     * @param content Content of the file.
     * @apiNote The provided path will be considered relative to the resource folder if not absolute
     */
    public static void write(String path, byte[] content) {
        assert nonNull(path);
        assert nonNull(content);
        try {
            Files.write(res_folder.resolve(path), content);
        } catch (IOException e){
            fail("An error occurred while trying to write to : '%s'", path);
        }
    }
    // ============================================================================================
    // ============================= IMAGE FILE MANIPULATION METHODS ==============================
    // ============================================================================================

    /**
     * Read an image from the disk.
     * The image can be one of the standard formats (png, jpeg ...)
     * @param path Path to the desired file
     * @return ARGB image representation
     * @apiNote The provided path will be considered relative to the resource folder if not absolute
     */
    public static int[][] readImage(String path) {
        assert nonNull(path);
        try {
            final var io = ImageIO.read(res_folder.resolve(path).toFile());
            final var width  = io.getWidth();
            final var height = io.getHeight();
            final var image = new int[height][width];
            for(var x = 0; x < height;++x)
                for(var y = 0; y < width; ++y)
                    image[x][y] = io.getRGB(y, x);
            return image;
        } catch (IOException e) {
            return fail("An error occurred while trying to read from : '%s'", path);
        }
    }

    /**
     * Write a <b>PNG</b> image to the disk.
     * @param path Path to the desired file
     * @param image Image in <b>ARGB</b> format to store
     * @apiNote The provided path will be considered relative to the resource folder if not absolute
     */
    public static void writeImage(String path, int[][] image) {
        assert nonNull(path);
        assert nonNull(image);
        try {
            final var buffer = new BufferedImage(image[0].length, image.length, BufferedImage.TYPE_4BYTE_ABGR);
            for(var x = 0; x < buffer.getHeight(); ++x){
                for(var y = 0 ; y < buffer.getWidth(); ++y){
                    buffer.setRGB(y, x, image[x][y]);
                }
            }
            ImageIO.write(buffer, "png", res_folder.resolve(path).toFile());
        } catch (IOException e) {
            fail("An error occurred while trying to write to : '%s'", path);
        }
    }

    // ============================================================================================
    // =================================== GRAPHIC WINDOWS ========================================
    // ============================================================================================

    /**
     * Open a graphical window to display an image
     * @param image Image to display in the <b>ARGB</b> format
     * @param title The title of the window
     */
    public static void show(int[][] image, String title) {
        // Build the Java representation of the image
        final var width = image[0].length;
        final var height = image.length;
        final var jimage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        for (var row = 0; row < height; ++row)
            for (var col = 0; col < width; ++col)
                jimage.setRGB(col, row, image[row][col]);

        // Instantiate a swing panel
        final var panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(jimage, 0, 0, Math.max(getWidth(), 100), Math.max(getHeight(), 100), null, null);
            }
        };

        // Build the frame
        final var frame = new JFrame(title);
        frame.add(panel);
        frame.getContentPane()
                .setPreferredSize(new Dimension(Math.max(jimage.getWidth(), 300), Math.max(jimage.getHeight(), 300)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                synchronized (frame) {
                    frame.notifyAll();
                }
            }
        });
        // Wait for the frame to be closed
        try {
            synchronized (frame) {
                while (frame.isVisible())
                    frame.wait();
            }
        } catch (InterruptedException e) {
            // Empty on purpose ;-)
        } finally {
            frame.dispose();
        }

    }

    /**
     * Open a graphical dialog window
     * @param title The title of the window
     * @param message The message to display on the window
     */
    public static void dialog(String title, String message){
        showMessageDialog(null, message, title, INFORMATION_MESSAGE);
    }


    // ============================================================================================
    // ==================================== PROGRAM MANIPULATION ==================================
    // ============================================================================================


    /**
     * Stop the execution of the program.
     *
     * @param fmt Formatted String to display as a message
     * @param args Arguments of the formatted String above
     * @return Nothing
     * @param <T> - Capture a type, so we can write 'return fail("")'
     */
    public static <T> T fail(String fmt, Object ... args){
        throw new RuntimeException(String.format(fmt, args));
    }

}