package io.github.linktosriram.s3lite.core.auth;

import io.github.linktosriram.s3lite.api.auth.AwsCredentials;

@FunctionalInterface
interface Signer {
    void sign(SignableRequest request, AwsCredentials credentials);
}
