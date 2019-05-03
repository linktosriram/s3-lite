package io.github.linktosriram.s3lite.http.spi.response;

import io.github.linktosriram.s3lite.http.spi.HttpStatus;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ImmutableResponse {

    HttpStatus getStatus();

    Map<String, List<String>> getHeaders();

    Optional<InputStream> getResponseBody();

    static Builder builder() {
        return new Builder();
    }

    class Builder {

        private HttpStatus status;
        private Map<String, List<String>> headers;
        private InputStream responseBody;

        Builder() {
        }

        public Builder status(final HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder headers(final Map<String, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public Builder responseBody(final InputStream responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public ImmutableResponse build() {
            return new DefaultImmutableResponse(status, headers, responseBody);
        }
    }
}
