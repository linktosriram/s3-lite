package io.github.linktosriram.s3lite.api.exception;

public class NoSuchBucketException extends S3Exception {

    public NoSuchBucketException(final ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
