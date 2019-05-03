package io.github.linktosriram.s3lite.api.response;

import java.io.FilterInputStream;
import java.io.InputStream;

public class ResponseInputStream<T> extends FilterInputStream {

    private final T response;

    ResponseInputStream(final T response, final InputStream in) {
        super(in);
        this.response = response;
    }

    public T getResponse() {
        return response;
    }
}
