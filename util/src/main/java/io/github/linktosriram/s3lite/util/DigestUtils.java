package io.github.linktosriram.s3lite.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class DigestUtils {

    private static final int STREAM_BUFFER_LENGTH = 1024;
    private static final String HMAC_SHA_256 = "HmacSHA256";
    private static final String SHA_256 = "SHA-256";

    private static final char[] DIGITS_LOWER =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private DigestUtils() {
        throw new AssertionError();
    }

    /**
     * Converts an array of bytes into a String representing the hexadecimal values of each byte in order. The returned
     * String will be double the length of the passed array, as it takes two characters to represent any given byte.
     *
     * @param data a byte[] to convert to Hex characters
     * @return A String containing lower-case hexadecimal characters
     */
    public static String encodeHexString(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return new String(out);
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha256Hex(final InputStream data) throws IOException {
        final MessageDigest digest = getSha256Digest();
        final byte[] bytes = updateDigest(digest, data).digest();
        return encodeHexString(bytes);
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     */
    public static String sha256Hex(final String data) {
        return encodeHexString(sha256(data));
    }

    /**
     * Returns the HMAC-SHA-256 digest for the input data using the key.
     *
     * @param key  the key to use
     * @param data the input to use, treated as UTF-8
     * @return the digest as a byte[]
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught or key is null or key is
     *                                  invalid.
     */
    public static byte[] hmacSha256(final byte[] key, final String data) {
        try {
            final Key keySpec = new SecretKeySpec(key, HMAC_SHA_256);
            final Mac mac = Mac.getInstance(HMAC_SHA_256);
            mac.init(keySpec);
            return mac.doFinal(data.getBytes(UTF_8));
        } catch (final NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[] sha256(final String data) {
        return getSha256Digest().digest(data.getBytes(UTF_8));
    }

    private static MessageDigest getSha256Digest() {
        try {
            return MessageDigest.getInstance(SHA_256);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static MessageDigest updateDigest(final MessageDigest digest, final InputStream data) throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1) {
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }

        return digest;
    }
}
