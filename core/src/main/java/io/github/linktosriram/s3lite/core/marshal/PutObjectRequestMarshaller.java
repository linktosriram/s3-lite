package io.github.linktosriram.s3lite.core.marshal;

import io.github.linktosriram.s3lite.api.request.PutObjectRequest;
import io.github.linktosriram.s3lite.core.auth.SignableRequest;

import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.ISO_INSTANT_FORMATTER;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.RFC_1123_DATE_TIME_FORMATTER;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addHeaderIfNotNull;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addMetadata;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.formatIfNotNull;

public class PutObjectRequestMarshaller implements SdkRequestMarshaller<PutObjectRequest> {

    @Override
    public void accept(final SignableRequest signableRequest, final PutObjectRequest request) {
        final String responseExpires = formatIfNotNull(request.getExpires(), RFC_1123_DATE_TIME_FORMATTER);
        signableRequest.addHeader("Expect", "100-continue");
        addMetadata(signableRequest, request.getMetadata());

        addHeaderIfNotNull(signableRequest, "x-amz-acl", request.getAcl());
        addHeaderIfNotNull(signableRequest, "Cache-Control", request.getCacheControl());
        addHeaderIfNotNull(signableRequest, "Content-Disposition", request.getContentDisposition());
        addHeaderIfNotNull(signableRequest, "Content-Encoding", request.getContentEncoding());
        addHeaderIfNotNull(signableRequest, "Content-Language", request.getContentLanguage());
        addHeaderIfNotNull(signableRequest, "Content-Length", request.getContentLength());
        addHeaderIfNotNull(signableRequest, "Content-MD5", request.getContentMD5());
        addHeaderIfNotNull(signableRequest, "Content-Type", request.getContentType());
        addHeaderIfNotNull(signableRequest, "Expires", responseExpires);
        addHeaderIfNotNull(signableRequest, "x-amz-grant-full-control", request.getGrantFullControl());
        addHeaderIfNotNull(signableRequest, "x-amz-grant-read", request.getGrantRead());
        addHeaderIfNotNull(signableRequest, "x-amz-grant-read-acp", request.getGrantReadACP());
        addHeaderIfNotNull(signableRequest, "x-amz-grant-write", request.getGrantWrite());
        addHeaderIfNotNull(signableRequest, "x-amz-grant-write-acp", request.getGrantWriteACP());
        addHeaderIfNotNull(signableRequest, "x-amz-object-lock-legal-hold", request.getObjectLockLegalHoldStatus());
        addHeaderIfNotNull(signableRequest, "x-amz-object-lock-mode", request.getObjectLockMode());
        addHeaderIfNotNull(signableRequest, "x-amz-object-lock-retain-until-date",
            formatIfNotNull(request.getObjectLockRetainUntilDate(), ISO_INSTANT_FORMATTER));
        addHeaderIfNotNull(signableRequest, "x-amz-request-payer", request.getRequestPayer());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption", request.getServerSideEncryption());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-customer-algorithm",
            request.getSseCustomerAlgorithm());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-customer-key", request.getSseCustomerKey());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-customer-key-MD5",
            request.getSseCustomerKeyMD5());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-context", request.getSseEncryptionContext());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-aws-kms-key-id", request.getSseKmsKeyId());
        addHeaderIfNotNull(signableRequest, "x-amz-storage-class", request.getStorageClass());
        addHeaderIfNotNull(signableRequest, "x-amz-tagging", request.getTagging());
        addHeaderIfNotNull(signableRequest, "x-amz-website-redirect-location", request.getWebsiteRedirectLocation());
    }
}
