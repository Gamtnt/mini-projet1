package ch.epfl.cs107;

import ch.epfl.cs107.crypto.Decrypt;
import ch.epfl.cs107.crypto.Encrypt;
import ch.epfl.cs107.stegano.ImageSteganography;
import ch.epfl.cs107.stegano.TextSteganography;
import ch.epfl.cs107.utils.Bit;
import ch.epfl.cs107.utils.Image;
import ch.epfl.cs107.utils.Text;

/**
 * Signatures of all the methods to be implemented by the students
 *
 * @apiNote DO NOT CHANGE THE CONTENT OF THIS CLASS. IF YOU DO CHANGE IT, YOUR SUBMISSION CAN BE REFUSED BY
 * THE SERVER OR THE AUTOMATIC GRADER WILL NOT GRADE YOUR PROJECT.
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public final class SignatureChecks {

    /**
     * DO NOT CHANGE THIS, MORE ON THAT IN WEEK 7.
     */
    private SignatureChecks(){}

    private int int_value;
    private byte byte_value;
    private boolean boolean_value;
    private String string_value;

    /**
     * Signature checks for {@link Bit}
     */
    public final class SigCheckBit {

        int res0 = Bit.embedInXthBit(int_value, boolean_value, int_value);
        int res1 = Bit.embedInLSB(int_value, boolean_value);
        boolean res2 = Bit.getXthBit(int_value, int_value);
        boolean res3 = Bit.getLSB(int_value);
        boolean[] res4 = Bit.toBitArray(byte_value);
        byte res5 = Bit.toByte(new boolean[0]);

    }

    /**
     * Signature checks for {@link Text}
     */
    public final class SigCheckText {

        boolean[] res0 = Text.toBitArray(string_value);
        String res1 = Text.toString(new boolean[0]);

    }

    /**
     * Signature checks for {@link Image}
     */
    public final class SigCheckImage {

        int res0 = Image.argb(byte_value, byte_value, byte_value, byte_value);
        int res1 = Image.alpha(int_value);
        int res2 = Image.red(int_value);
        int res3 = Image.green(int_value);
        int res4 = Image.blue(int_value);
        int res5 = Image.gray(int_value);
        boolean res6 = Image.binary(int_value, int_value);
        int[][] res7 = Image.toGray(new int[0][0]);
        boolean[][] res8 = Image.toBinary(new int[0][0], int_value);
        int[][] res9 = Image.fromGray(new int[0][0]);
        int[][] res10 = Image.fromBinary(new boolean[0][0]);

    }

    /**
     * Signature checks for {@link Encrypt}
     */
    public final class SigCheckEncrypt {

        byte[] res0 = Encrypt.caesar(new byte[0], byte_value);
        byte[] res1 = Encrypt.vigenere(new byte[0], new byte[0]);
        byte[] res2 = Encrypt.cbc(new byte[0], new byte[0]);
        byte[] res3 = Encrypt.xor(new byte[0], byte_value);
        byte[] res4 = Encrypt.oneTimePad(new byte[0], new byte[0]);

    }

    /**
     * Signature checks for {@link Decrypt}
     */
    public final class SigCheckDecrypt {

        byte[] res0 = Decrypt.caesar(new byte[0], byte_value);
        byte[] res1 = Decrypt.vigenere(new byte[0], new byte[0]);
        byte[] res2 = Decrypt.cbc(new byte[0], new byte[0]);
        byte[] res3 = Decrypt.xor(new byte[0], byte_value);
        byte[] res4 = Decrypt.oneTimePad(new byte[0], new byte[0]);

    }

    /**
     * Signature checks for {@link ImageSteganography}
     */
    public final class SigCheckImageSteganography {

        int[][] res0 = ImageSteganography.embedARGB(new int[0][0], new int[0][0], int_value);
        int[][] res1 = ImageSteganography.embedGray(new int[0][0], new int[0][0], int_value);
        int[][] res2 = ImageSteganography.embedBW(new int[0][0], new boolean[0][0]);
        boolean[][] res3 = ImageSteganography.revealBW(new int[0][0]);

    }

    /**
     * Signature checks for {@link TextSteganography}
     */
    public final class SigCheckTextSteganography {

        int[][] res0 = TextSteganography.embedBitArray(new int[0][0], new boolean[0]);
        boolean[] res1 = TextSteganography.revealBitArray(new int[0][0]);
        int[][] res2 = TextSteganography.embedText(new int[0][0], new byte[0]);
        byte[] res3 = TextSteganography.revealText(new int[0][0]);

    }

    /**
     * Signature checks for {@link Challenge}
     */
    public final class SigCheckChallenge{
        String res0 = Challenge.challenge();
    }

}
