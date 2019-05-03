package io.github.linktosriram.s3lite.api.response;

import io.github.linktosriram.s3lite.http.spi.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Interface for processing a streaming response body from a service in a synchronous fashion.
 * For processing headers, please have a look at {@code SdkResponseUnmarshaller}
 *
 * @param <T>
 */
@FunctionalInterface
public interface ResponseTransformer<T, R> {

    /**
     * Process the response contents.
     *
     * @param input Input stream of streamed data.
     * @return Transformed type.
     */
    R transform(T response, InputStream input) throws IOException;

    /**
     * Hook to allow connection to be left open after the SDK returns a response. Useful for returning the
     * InputStream to the response content from the transformer.
     *
     * @return {@code true} if connection (and InputStream) should be left open after the SDK returns a response
     * otherwise.
     */
    default boolean needsConnectionLeftOpen() {
        return false;
    }

    static <T> ResponseTransformer<T, T> toPath(final Path path) {
        return (resp, in) -> {
            Files.copy(in, path);
            return resp;
        };
    }

    static <T> ResponseTransformer<T, T> toFile(final File file) {
        return toPath(file.toPath());
    }

    static <T> ResponseTransformer<T, T> toOutputStream(final OutputStream outputStream) {
        return (resp, in) -> {
            IOUtils.copy(in, outputStream);
            return resp;
        };
    }

    static <T> ResponseTransformer<T, ResponseBytes<T>> toBytes() {
        return (resp, in) -> ResponseBytes.fromByteArray(resp, IOUtils.toByteArray(in));
    }

    static <T> ResponseTransformer<T, ResponseInputStream<T>> toInputStream() {
        final ResponseTransformer<T, ResponseInputStream<T>> transformer = ResponseInputStream::new;
        return new ResponseTransformer<T, ResponseInputStream<T>>() {
            @Override
            public ResponseInputStream<T> transform(final T response, final InputStream input) throws IOException {
                return transformer.transform(response, input);
            }

            @Override
            public boolean needsConnectionLeftOpen() {
                return true;
            }
        };
    }
}
