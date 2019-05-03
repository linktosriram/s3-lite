package io.github.linktosriram.s3lite.http.spi;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;

    private IOUtils() {
        throw new AssertionError();
    }

    /**
     * Gets the contents of an {@code InputStream} as a {@code byte[]}.
     * <p>
     * This method buffers the input internally, so there is no need to use a {@code BufferedInputStream}.
     *
     * @param input the {@code InputStream} to read from
     * @return the requested byte array
     * @throws IOException if an I/O error occurs
     */
    public static byte[] toByteArray(final InputStream input) throws IOException {
        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int n;
            while ((n = input.read(buffer)) != EOF) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        }
    }

    /**
     * Copies bytes from an {@code InputStream} to an {@code OutputStream}.
     * This method buffers the input internally, so there is no need to use a {@code BufferedInputStream}
     *
     * @param input  the {@code InputStream} to read from
     * @param output the {@code OutputStream} to write to
     * @throws IOException if an I/O error occurs
     */
    public static void copy(final InputStream input, final OutputStream output) throws IOException {
        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n;
        while ((n = input.read(buffer)) != EOF) {
            output.write(buffer, 0, n);
        }
    }

    /**
     * Closes a {@code Closeable} unconditionally.
     *
     * @param closeable closeable the objects to close, may be null or already closed
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ignored) {
        }
    }
}
