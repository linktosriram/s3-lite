package io.github.linktosriram.s3lite.core.marshal;

import io.github.linktosriram.s3lite.api.request.GetObjectRequest;
import io.github.linktosriram.s3lite.core.auth.SignableRequest;

import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.RFC_1123_DATE_TIME_FORMATTER;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addHeaderIfNotNull;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addParameterIfNotNull;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.formatIfNotNull;

public class GetObjectRequestMarshaller implements SdkRequestMarshaller<GetObjectRequest> {

    @Override
    public void accept(final SignableRequest signableRequest, final GetObjectRequest request) {
        final String responseExpires = formatIfNotNull(request.getResponseExpires(), RFC_1123_DATE_TIME_FORMATTER);
        final String ifModifiedSince = formatIfNotNull(request.getIfModifiedSince(), RFC_1123_DATE_TIME_FORMATTER);
        final String ifUnmodifiedSince = formatIfNotNull(request.getIfUnmodifiedSince(), RFC_1123_DATE_TIME_FORMATTER);
        final String range = request.getRange();

        addParameterIfNotNull(signableRequest, "response-content-type", request.getResponseContentType());
        addParameterIfNotNull(signableRequest, "response-content-language", request.getResponseContentLanguage());
        addParameterIfNotNull(signableRequest, "response-expires", responseExpires);
        addParameterIfNotNull(signableRequest, "response-cache-control", request.getResponseCacheControl());
        addParameterIfNotNull(signableRequest, "response-content-encoding", request.getResponseContentEncoding());
        addParameterIfNotNull(signableRequest, "response-content-disposition", request.getResponseContentDisposition());
        addParameterIfNotNull(signableRequest, "versionId", request.getVersionId());
        addParameterIfNotNull(signableRequest, "partNumber", request.getPartNumber());

        if (range != null) {
            signableRequest.addHeader("Range", "bytes=" + range);
        }

        addHeaderIfNotNull(signableRequest, "If-Modified-Since", ifModifiedSince);
        addHeaderIfNotNull(signableRequest, "If-Unmodified-Since", ifUnmodifiedSince);
        addHeaderIfNotNull(signableRequest, "If-Match", request.getIfMatch());
        addHeaderIfNotNull(signableRequest, "If-None-Match", request.getIfNoneMatch());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-customer-algorithm",
            request.getSseCustomerAlgorithm());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-customer-key", request.getSseCustomerKey());
        addHeaderIfNotNull(signableRequest, "x-amz-server-side-encryption-customer-key-MD5",
            request.getSseCustomerKeyMD5());
        addHeaderIfNotNull(signableRequest, "x-amz-request-payer", request.getRequestPayer());
    }
}
