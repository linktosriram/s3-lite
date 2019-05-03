package io.github.linktosriram.s3lite.api.exception;

public class S3Exception extends RuntimeException {

    private final String code;

    private final String requestId;

    private final String hostId;

    public S3Exception(final ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        code = errorResponse.getCode();
        requestId = errorResponse.getRequestId();
        hostId = errorResponse.getHostId();
    }

    public String getCode() {
        return code;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getHostId() {
        return hostId;
    }
}
