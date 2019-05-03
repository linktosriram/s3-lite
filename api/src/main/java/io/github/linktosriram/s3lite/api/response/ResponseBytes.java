package io.github.linktosriram.s3lite.api.response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * An in-memory representation of the service's response from a streaming operation. This usually obtained by calling
 * the "bytes" form of a streaming operation, like S3's {@code getObjectBytes}. Can also be retrieved by passing
 * {@link ResponseTransformer#toBytes()} to a streaming output operation.
 */
public class ResponseBytes<T> {

    private final T response;

    private final byte[] bytes;

    private ResponseBytes(final T response, final byte[] bytes) {
        this.response = response;
        this.bytes = bytes;
    }

    public T getResponse() {
        return response;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String asString(final Charset charset) {
        return new String(bytes, charset);
    }

    public String asUtf8String() {
        return asString(UTF_8);
    }

    public InputStream asInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(bytes).asReadOnlyBuffer();
    }

    static <T> ResponseBytes<T> fromByteArray(final T response, final byte[] bytes) {
        return new ResponseBytes<>(response, Arrays.copyOf(bytes, bytes.length));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final ResponseBytes<?> that = (ResponseBytes<?>) obj;
        return Objects.equals(response, that.response) &&
            Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(response);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseBytes{" +
            "response=" + response +
            ", bytes=" + Arrays.toString(bytes) +
            '}';
    }
}
