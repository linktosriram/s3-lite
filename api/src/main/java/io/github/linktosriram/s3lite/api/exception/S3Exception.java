package io.github.linktosriram.s3lite.api.exception;

public class S3Exception extends RuntimeException {

    private final String code;

    private final String requestId;

    private final String resource;

    public S3Exception(final ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        code = errorResponse.getCode();
        requestId = errorResponse.getRequestId();
        resource = errorResponse.getResource();
    }

    public String getCode() {
        return code;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getResource() {
        return resource;
    }
}
