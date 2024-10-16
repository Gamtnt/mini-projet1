package ch.epfl.cs107;

import ch.epfl.cs107.crypto.Decrypt;
import ch.epfl.cs107.crypto.Encrypt;
import ch.epfl.cs107.stegano.ImageSteganography;
import ch.epfl.cs107.stegano.TextSteganography;
import ch.epfl.cs107.utils.Bit;
import ch.epfl.cs107.utils.Image;
import ch.epfl.cs107.utils.Text;

import java.io.File;
import java.util.Arrays;

/**
 * Entry point of the program
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public final class Main {

        /*
            We've listed all the test methods here.
            Once you've implemented a new functionality, you can uncomment
            the corresponding test and run it.
            All the test starts with the 'assert' keyword. This means that if
            a test passes, your program will continue the execution of the code.
            Otherwise, if the test fails, your program will stop and a message will
            appear in your terminal:
            """
            Exception in thread "main" java.lang.AssertionError
            """
            You can check which test fails and why by inspecting the StackTrace.
            You can always change the code of this method to change the behavior of
            your program.
            THESE TESTS ARE NOT EXHAUSTIVE AND YOU ARE ENCOURAGED TO COMPLETE THEM
             */

    public static final int IMAGE_THRESHOLD = 128;

    // DO NOT CHANGE THIS, MORE ON THAT ON WEEK 7
    private Main() {}

    /**
     * Entry point of the program
     * @param args (String[]) - command line arguments
     * @implNote PLEASE UNCOMMENT EACH ASSERTION TO CHECK IF YOUR IMPLEMENTATION IS WORKING
     */
    public static void main(String[] args) {
        // ========== Test Bit ==========
        //assert testXthBit();
        //assert testGetLSB();
        //assert testEmbedInXthBit();
        //assert testEmbedInLSB();
        //assert testByteConversion();
        // ========== Test Text ==========
        //assert testToBitArray();
        Helper.dialog("Tests", "Bit and Text manipulation passed");
        // ========== Test Image ==========
        //assert testConversionARGBInt();
        //assert testPixelToGray();
        //assert testGrayToBinary();
        //assert testImageToGray();
        //assert testGrayImageToBinary();
        //assert testImageFromGray();
        //assert testImageFromBinary();
        Helper.dialog("Tests ", "Image manipulation passed");
        //assert testWithRealImage("image-formats");
        //assert testBinaryWithRealImage("image-formats");
        Helper.dialog("Tests ", "Image manipulation with images from 'image-formats' passed");
        // ========== Test Cryptography Methods ==========
        String message = "La vie est un long fleuve tranquille :-)";
        String key = "2cF%5";
        //testCrypto(message, key);
        //message = Text.toString(Helper.read("text_one.txt"));
        //testCrypto(message, key);
        Helper.dialog("Tests ", "Cryptography passed");
        // ========== Test Steganography Methods ==========
        //assert testEmbedBWImage();
        //assert testEmbedText();
        //assert testImageSteganographyWithImages("the-starry-night");
        //assert testRevealBitArray();
        Helper.dialog("Tests ", "ImageSteganography passed");
    }

    // ============================================================================================
    // ======================================== BIT ===============================================
    // ============================================================================================

    private static boolean testXthBit() {
        boolean[] expected = {
                false, false, true, false, true, true, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true
        };
        int value = -12;
        boolean[] computed = new boolean[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; ++i) {
            computed[i] = Bit.getXthBit(value, i);
        }

        return Arrays.equals(computed, expected);
    }

    private static boolean testGetLSB() {
        int[] values = {-12, 12, 0, 255};
        boolean[] expected = {false, false, false, true};
        boolean[] computed = new boolean[values.length];
        for (int i = 0; i < computed.length; ++i) {
            computed[i] = Bit.getLSB(values[i]);
        }
        return Arrays.equals(computed, expected);
    }

    private static boolean testEmbedInXthBit() {
        int value = -12;
        int computed = Bit.embedInXthBit(value, false, 2);
        int expected = -16;
        return computed == expected;
    }

    private static boolean testEmbedInLSB() {
        int value = -12;
        int computed = Bit.embedInLSB(value, true);
        int expected = -11;
        return computed == expected;
    }

    private static boolean testByteConversion() {
        byte value = -4;
        boolean[] computedBooleanArray = Bit.toBitArray(value);
        //System.err.println(Arrays.toString(computedBooleanArray)); // uncomment to debug
        byte computedByte = Bit.toByte(computedBooleanArray);
        return computedByte == value;
    }

    // ============================================================================================
    // ======================================== TEXT ==============================================
    // ============================================================================================

    private static boolean testToBitArray() {
        String message = "ô$";
        boolean[] computed = Text.toBitArray(message);
        boolean[] expected = {
                true, true, false, false, false, false,
                true, true, true, false, true, true,
                false, true, false, false, false,
                false, true, false, false, true, false, false};
        return Arrays.equals(computed, expected);
    }

    // ============================================================================================
    // ======================================== IMAGE =============================================
    // ============================================================================================

    private static boolean testConversionARGBInt() {
        int color = 0b11111111_11110000_00001111_01010101;
        byte alphaByte = Image.alpha(color);
        byte redByte = Image.red(color);
        byte greenByte = Image.green(color);
        byte blueByte = Image.blue(color);
        /*
        //uncomment to debug
        System.out.println(color); // -1044651
        System.out.println(redByte); // -16
        System.out.println(greenByte); // 15
        System.out.println(blueByte); // 85
        */
        return (alphaByte == -1 && redByte == -16 &&
                greenByte == 15 && blueByte == 85 &&
                Image.argb(alphaByte, redByte, greenByte, blueByte) == color);
    }

    private static boolean testPixelToGray() {
        int color = 0b11111111_11110000_00001111_01010101; // 2146438997 (0xFFF00F55)
        return Image.gray(color) == 113; // (240 + 15 + 85)/3
    }

    private static boolean testGrayToBinary() {
        int gray = 113;
        return (Image.binary(gray, gray) && !Image.binary(gray, gray + 1));
    }

    private static boolean testImageToGray() {
        int[][] image = {
                {0x0020c0ff, 0x00123456},  // [[2146559, 1193046]
                {0xffffffff, 0x000000000}  //  [-1     , 0]]
        };

        int[][] computed = Image.toGray(image);
        //System.out.println(Arrays.deepToString(computed)); // uncomment to debug
        int[][] expected = {{159, 52}, {255, 0}};
        return Arrays.deepEquals(computed, expected);
    }

    private static boolean testGrayImageToBinary() {
        int[][] grayImage = {{159, 52}, {255, 0}};
        boolean[][] computed = Image.toBinary(grayImage, 100);
        boolean[][] expected = {{true, false}, {true, false}};
        return Arrays.deepEquals(computed, expected);
    }

    private static boolean testImageFromGray() {
        int[][] grayImage = {{159, 52}, {255, 0}}; // {{0xf9, 0x34}, {0xff, 0x00}}
        int[][] computed = Image.fromGray(grayImage);
        int[][] expected = {{0xff9f9f9f, 0xff343434}, {0xffffffff, 0xff000000}};
        return Arrays.deepEquals(computed, expected);
    }

    private static boolean testImageFromBinary() {
        boolean[][] binaryImage = {{false, true}, {true, false}};
        int[][] computed = Image.fromBinary(binaryImage);
        int[][] expected = {{0xff000000, 0xffffffff}, {0xffffffff, 0xff000000}};
        return Arrays.deepEquals(computed, expected);
    }

    private static boolean testWithRealImage(String path){
        var coloured = Helper.readImage(path + File.separator + "argb.png");
        Helper.show(coloured, "ARGB for " + path);
        var grayscaled = Helper.readImage(path + File.separator + "gray.png");
        Helper.show(grayscaled, "Gray for " + path);
        return Arrays.deepEquals(Image.fromGray(Image.toGray(coloured)), grayscaled);
    }

    private static boolean testBinaryWithRealImage(String path){
        var image = Helper.readImage(path + File.separator + "argb.png");
        var grayscaled = Image.toGray(image);
        return Arrays.deepEquals(Image.fromBinary(Image.toBinary(grayscaled, 100)), Helper.readImage(path + File.separator + "binary100.png")) &&
                Arrays.deepEquals(Image.fromBinary(Image.toBinary(grayscaled, 150)), Helper.readImage(path + File.separator + "binary150.png")) &&
                Arrays.deepEquals(Image.fromBinary(Image.toBinary(grayscaled, 200)), Helper.readImage(path + File.separator + "binary200.png")) &&
                Arrays.deepEquals(Image.fromBinary(Image.toBinary(grayscaled, 220)), Helper.readImage(path + File.separator + "binary220.png"));
    }

    // ============================================================================================
    // ===================================== CRYPTOGRAPHY =========================================
    // ============================================================================================

    private static boolean testCaesar(byte[] string, byte key) {
        //Encoding with key
        byte[] result = Encrypt.caesar(string, key);
        //Decoding with key
        byte[] decryptedAsBytes = Decrypt.caesar(result, key);
        String decryptedAsString = Text.toString(decryptedAsBytes);
        //System.out.println("Decoded : " + decryptedAsString); // uncomment to debug
        return Arrays.equals(string, decryptedAsBytes);
    }

    private static boolean testXor(byte[] string, byte key) {
        //Encoding with key
        byte[] result = Encrypt.xor(string, key);
        //Decoding with key
        byte[] decryptedAsBytes = Decrypt.xor(result, key);
        String decryptedAsString = Text.toString(decryptedAsBytes);
        //System.out.println("Decoded : " + decryptedAsString); // uncomment to debug
        return Arrays.equals(string, decryptedAsBytes);
    }

    private static boolean testVigenere(byte[] string, byte[] key) {
        //Encoding with key
        byte[] result = Encrypt.vigenere(string, key);
        //Decoding with key
        byte[] decryptedAsBytes = Decrypt.vigenere(result, key);
        String decryptedAsString = Text.toString(decryptedAsBytes);
        //System.out.println("Decoded : " + decryptedAsString); // uncomment to debug
        return Arrays.equals(string, decryptedAsBytes);
    }

    private static boolean testOneTimePad(byte[] string) {
        //Encoding with key
        byte[] result = Encrypt.oneTimePad(string, string);

        //Decoding with key
        byte[] decryptedAsBytes = Decrypt.oneTimePad(result, string);
        String decryptedAsString = Text.toString(decryptedAsBytes);
        //System.out.println("Decoded : " + decryptedAsString); // uncomment to debug
        return Arrays.equals(string, decryptedAsBytes);
    }

    private static boolean testCBC(byte[] string, byte[] iv) {
        //Encoding with key
        byte[] result = Encrypt.cbc(string, string);

        //Decoding with key
        byte[] decryptedAsBytes = Decrypt.cbc(result, string);
        String decryptedAsString = Text.toString(decryptedAsBytes);
        //System.out.println("Decoded : " + decryptedAsString); // uncomment to debug
        return Arrays.equals(string, decryptedAsBytes);
    }

    // This might help you resolve the challenge ;-)
    private static void testCrypto(String message, String key) {
        byte[] byteMessage = Text.toBytes(message);
        byte[] byteKey = Text.toBytes(key);
        assert testCaesar(byteMessage, byteKey[0]);
        assert testXor(byteMessage, byteKey[0]);
        assert testVigenere(byteMessage, byteKey);
        assert testOneTimePad(byteMessage);
        assert testCBC(byteMessage, byteKey);
    }

    // ============================================================================================
    // ================================== IMAGE STEGANOGRAPHY =====================================
    // ============================================================================================

    private static boolean testEmbedBWImage() {
        final boolean[][] BIT_IMAGE =
                {
                        {false, false, true, true, true, true, true, true, true, true},
                        {false, false, true, true, true, true, true, true, false, false},
                        {false, true, true, true, true, true, true, false, false, true},
                        {false, true, true, true, true, true, false, false, false, true},
                        {false, true, true, true, true, false, false, false, true, true},
                        {false, true, true, false, false, false, false, true, true, true},
                        {false, true, false, false, false, false, true, true, true, true},
                        {false, false, false, false, false, true, true, true, true, true},
                        {false, false, false, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true, true}
                };
        int[][] EXPECTED_IMAGE =
                {
                        { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 0, 0 },
                        { 0, 1, 1, 1, 1, 1, 1, 0, 0, 1 }, { 0, 1, 1, 1, 1, 1, 0, 0, 0, 1 },
                        { 0, 1, 1, 1, 1, 0, 0, 0, 1, 1 }, { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1 },
                        { 0, 1, 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
                        { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
                };
        int[][] cover = new int[10][10]; // filled with 0

        int[][] hidden = ImageSteganography.embedBW(cover, BIT_IMAGE);
        return Arrays.deepEquals(EXPECTED_IMAGE, hidden) && Arrays.deepEquals(cover, new int[10][10]);
    }

    private static boolean testEmbedText() {
        final byte[] TEXT = Text.toBytes("$\\|");
        int[][] cover = new int[10][10]; // filled with 0
        final int[][] EXPECTED_IMAGE = {{0, 0, 1, 0, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 0, 0, 0, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }};

        int[][] hidden = TextSteganography.embedText(cover, TEXT);
        return Arrays.deepEquals(EXPECTED_IMAGE, hidden) && Arrays.deepEquals(cover, new int[10][10]);
    }

    private static boolean testImageSteganographyWithImages(String path){
        var image  = Helper.readImage(path + File.separator + "image.png");
        var cover  = Helper.readImage(path + File.separator + "cover.png");
        var hidden = Helper.readImage(path + File.separator + "hidden.png");
        return Arrays.deepEquals(ImageSteganography.embedARGB(cover, image, IMAGE_THRESHOLD), hidden);
    }

    private static boolean testRevealBitArray() {
        var image = new int[][]{{914510701, -1532355920}, {-472952826, 483441963}};
        var expected = new boolean[]{true, false, false, true};
        var actual = TextSteganography.revealBitArray(image);

        return Arrays.equals(expected, actual);
    }

}
