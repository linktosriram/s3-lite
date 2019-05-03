package io.github.linktosriram.s3lite.core.marshal;

import io.github.linktosriram.s3lite.api.request.ListObjectsV2Request;
import io.github.linktosriram.s3lite.core.auth.SignableRequest;

import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addHeaderIfNotNull;
import static io.github.linktosriram.s3lite.core.marshal.MarshallerUtils.addParameterIfNotNull;

public class ListObjectsV2RequestMarshaller implements SdkRequestMarshaller<ListObjectsV2Request> {

    @Override
    public void accept(final SignableRequest signableRequest, final ListObjectsV2Request request) {
        signableRequest.addParameter("list-type", "2");

        addParameterIfNotNull(signableRequest, "max-keys", request.getMaxKeys());
        addParameterIfNotNull(signableRequest, "fetch-owner", request.getFetchOwner());
        addParameterIfNotNull(signableRequest, "delimiter", request.getDelimiter());
        addParameterIfNotNull(signableRequest, "encoding-type", request.getEncodingType());
        addParameterIfNotNull(signableRequest, "prefix", request.getPrefix());
        addParameterIfNotNull(signableRequest, "continuation-token", request.getContinuationToken());
        addParameterIfNotNull(signableRequest, "start-after", request.getStartAfter());

        addHeaderIfNotNull(signableRequest, "x-amz-request-payer", request.getRequestPayer());
    }
}
