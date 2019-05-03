package io.github.linktosriram.s3lite.http.spi.request;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Represents the body of an HTTP request. Must be provided for operations that have a streaming input.
 * Offers various convenience factory methods from common sources of data (File, String, byte[], etc).
 */
public class RequestBody {

    private final Supplier<InputStream> contentStreamProvider;
    private final long contentLength;

    private RequestBody(final Supplier<InputStream> contentStreamProvider, final long contentLength) {
        this.contentStreamProvider = contentStreamProvider;
        this.contentLength = contentLength;
    }

    public Supplier<InputStream> getContentStreamProvider() {
        return contentStreamProvider;
    }

    public long getContentLength() {
        return contentLength;
    }

    public static RequestBody fromPath(final Path path) {
        try {
            return new RequestBody(() -> {
                try {
                    return Files.newInputStream(path);
                } catch (final IOException e) {
                    throw new UncheckedIOException(e);
                }
            }, Files.size(path));
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static RequestBody fromFile(final File file) {
        return fromPath(file.toPath());
    }

    public static RequestBody fromInputStream(final Supplier<InputStream> contentStreamProvider,
                                              final long contentLength) {
        return new RequestBody(contentStreamProvider, contentLength);
    }

    public static RequestBody fromBytes(final byte[] bytes) {
        return new RequestBody(() -> new ByteArrayInputStream(bytes), bytes.length);
    }

    public static RequestBody fromString(final String contents, final Charset charset) {
        return fromBytes(contents.getBytes(charset));
    }

    public static RequestBody fromString(final String contents) {
        return fromString(contents, UTF_8);
    }

    public static RequestBody empty() {
        return fromBytes(new byte[0]);
    }
}
