package io.github.linktosriram.s3lite.api.exception;

import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/ErrorResponses.html
 */
public class ErrorResponse {

    private final String code;

    private final String message;

    private final String requestId;

    private final String resource;

    private ErrorResponse(final Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.requestId = builder.requestId;
        this.resource = builder.resource;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getResource() {
        return resource;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(message, that.message) &&
            Objects.equals(requestId, that.requestId) &&
            Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, requestId, resource);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", requestId='" + requestId + '\'' +
            ", resource='" + resource + '\'' +
            '}';
    }

    public static class Builder {

        private String code;

        private String message;

        private String requestId;

        private String resource;

        private Builder() {
        }

        public Builder code(final String code) {
            this.code = code;
            return this;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder requestId(final String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder resource(final String resource) {
            this.resource = resource;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
