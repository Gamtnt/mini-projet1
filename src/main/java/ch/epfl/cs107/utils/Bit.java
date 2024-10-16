package ch.epfl.cs107.utils;

import ch.epfl.cs107.Helper;

import static ch.epfl.cs107.utils.Text.*;
import static ch.epfl.cs107.utils.Image.*;
import static ch.epfl.cs107.utils.Bit.*;
import static ch.epfl.cs107.stegano.ImageSteganography.*;
import static ch.epfl.cs107.stegano.TextSteganography.*;
import static ch.epfl.cs107.crypto.Encrypt.*;
import static ch.epfl.cs107.crypto.Decrypt.*;
import static ch.epfl.cs107.Main.*;

/**
 * <b>Task 1.1: </b>Utility class to manipulate bits
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Bit {

    // DO NOT CHANGE THIS, MORE ON THAT ON WEEK 7
    private Bit(){}

    // ============================================================================================
    // ==================================== BIT MANIPULATION ======================================
    // ============================================================================================

    /**
     * Embed a bit in a given integer bits
     * <p>
     * @param value value to embed in
     * @param m <code>true</code> to embed 1, <code>false</code> to embed 0
     * @param pos position of the bit to change
     * @return embedded value
     */
    public static int embedInXthBit(int value, boolean m, int pos) {
        return Helper.fail("NOT IMPLEMENTED");
    }

    /**
     * Embed a bit in the "least significant bit" (LSB)
     * <p>
     * @param value value to embed in
     * @param m <code>true</code> to embed 1, <code>false</code> to embed 0
     * @return embedded value
     */
    public static int embedInLSB(int value, boolean m){
        return Helper.fail("NOT IMPLEMENTED");
    }

    /**
     * Extract a bit in a given position from a given value
     * <p>
     * @param value value to extract from
     * @param pos position of the bit to extract
     * @return <code>true</code> if the bit is '1' and <code>false</code> otherwise
     */
    public static boolean getXthBit(int value, int pos) {
        return Helper.fail("NOT IMPLEMENTED");
    }

    /**
     * Extract the 'least significant bit' from a given value
     * <p>
     * @param value value to extract from
     * @return <code>true</code> if the bit is '1' and <code>false</code> otherwise
     */
    public static boolean getLSB(int value) {
        return Helper.fail("NOT IMPLEMENTED");
    }

    // ============================================================================================
    // ==================================== BYTE MANIPULATION =====================================
    // ============================================================================================

    /**
     * Convert a byte value to the bit array representation.
     * <p>
     * Suppose we have the following input <b><code>0b00_11_01_10</code></b>. We can expect this function
     * to return the following array :
     * <b>[<code>false</code>,
     *     <code>false</code>,
     *     <code>true</code>,
     *     <code>true</code>,
     *     <code>false</code>,
     *     <code>true</code>,
     *     <code>true</code>,
     *     <code>false</code>]</b>
     * @param value byte representation of the value
     * @return bit array representation of the value
     */
    public static boolean[] toBitArray(byte value){
        return Helper.fail("NOT IMPLEMENTED");
    }

    /**
     * Convert a boolean array to a byte
     * <p>
     * Suppose we have the following input :
     * <b>[<code>false</code>,
     *     <code>false</code>,
     *     <code>true</code>,
     *     <code>true</code>,
     *     <code>false</code>,
     *     <code>true</code>,
     *     <code>true</code>,
     *     <code>false</code>]</b>
     * We can expect this function to return the following byte : <b><code>0b00_11_01_10</code></b>.
     * @param bitArray bit array representation to convert
     * @return the byte representation of the bit array
     */
    public static byte toByte(boolean[] bitArray){
        return Helper.fail("NOT IMPLEMENTED");
    }

}
