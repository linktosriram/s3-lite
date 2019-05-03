package io.github.linktosriram.s3lite.http.spi.response;

import io.github.linktosriram.s3lite.http.spi.HttpStatus;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

class DefaultImmutableResponse implements ImmutableResponse {

    private final HttpStatus status;
    private final Map<String, List<String>> headers;
    private final InputStream responseBody;

    DefaultImmutableResponse(final HttpStatus status, final Map<String, List<String>> headers,
                             final InputStream responseBody) {
        this.status = status;
        this.headers = headers;
        this.responseBody = responseBody;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    @Override
    public Optional<InputStream> getResponseBody() {
        return ofNullable(responseBody);
    }
}
