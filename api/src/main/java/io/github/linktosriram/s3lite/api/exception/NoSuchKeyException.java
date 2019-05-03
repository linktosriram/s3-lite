package io.github.linktosriram.s3lite.api.exception;

public class NoSuchKeyException extends S3Exception {

    public NoSuchKeyException(final ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
