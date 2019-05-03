package io.github.linktosriram.s3lite.core.marshal;

import io.github.linktosriram.s3lite.api.request.DeleteObjectRequest;
import io.github.linktosriram.s3lite.core.auth.SignableRequest;

import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addHeaderIfNotNull;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addParameterIfNotNull;

public class DeleteObjectRequestMarshaller implements SdkRequestMarshaller<DeleteObjectRequest> {

    @Override
    public void accept(final SignableRequest signableRequest, final DeleteObjectRequest request) {
        addParameterIfNotNull(signableRequest, "versionId", request.getVersionId());

        addHeaderIfNotNull(signableRequest, "x-amz-mfa", request.getMfa());
        addHeaderIfNotNull(signableRequest, "x-amz-request-payer", request.getRequestPayer());
        addHeaderIfNotNull(signableRequest, "x-amz-bypass-governance-retention",
            request.getBypassGovernanceRetention());
    }
}
