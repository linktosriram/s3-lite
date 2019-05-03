package io.github.linktosriram.s3lite.core.unmarshal;

import io.github.linktosriram.s3lite.api.response.PutObjectResponse;

import java.util.List;
import java.util.Map;

import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.getHeaderOrNull;

public class PutObjectResponseUnmarshaller implements SdkResponseUnmarshaller<PutObjectResponse> {

    @Override
    public PutObjectResponse apply(final Map<String, List<String>> headers) {
        final String eTag = getHeaderOrNull(headers, "ETag");
        final String expiration = getHeaderOrNull(headers, "x-amz-expiration");
        final String requestCharged = getHeaderOrNull(headers, "x-amz-request-charged");
        final String serverSideEncryption = getHeaderOrNull(headers, "x-amz-server-side-encryption");
        final String sseCustomerAlgorithm = getHeaderOrNull(headers, "x-amz-server-side-encryption-customer-algorithm");
        final String sseCustomerKeyMD5 = getHeaderOrNull(headers, "x-amz-server-side-encryption-customer-key-MD5");
        final String sseKmsKeyId = getHeaderOrNull(headers, "x-amz-server-side-encryption-aws-kms-key-id");
        final String versionId = getHeaderOrNull(headers, "x-amz-version-id");

        return PutObjectResponse.builder()
            .eTag(eTag)
            .expiration(expiration)
            .requestCharged(requestCharged)
            .serverSideEncryption(serverSideEncryption)
            .sseCustomerAlgorithm(sseCustomerAlgorithm)
            .sseCustomerKeyMD5(sseCustomerKeyMD5)
            .sseKmsKeyId(sseKmsKeyId)
            .versionId(versionId)
            .build();
    }
}
