package io.github.linktosriram.s3lite.core.auth;

final class SignerConstants {
    static final String HOST = "Host";
    static final String LINE_SEPARATOR = "\n";
    static final String AWS4_SIGNING_ALGORITHM = "AWS4-HMAC-SHA256";
    static final String AWS4_TERMINATOR = "aws4_request";
    static final String X_AMZ_DATE = "X-Amz-Date";
    static final String X_AMZ_CONTENT_SHA256 = "X-Amz-Content-Sha256";
    static final String AUTHORIZATION = "Authorization";

    private SignerConstants() {
        throw new AssertionError();
    }
}
