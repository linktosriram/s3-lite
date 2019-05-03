package io.github.linktosriram.s3lite.core.unmarshal;

import io.github.linktosriram.s3lite.api.response.DeleteObjectResponse;

import java.util.List;
import java.util.Map;

import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.getHeader;
import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.getHeaderOrNull;

public class DeleteObjectResponseUnmarshaller implements SdkResponseUnmarshaller<DeleteObjectResponse> {

    @Override
    public DeleteObjectResponse apply(final Map<String, List<String>> headers) {
        final Boolean deleteMarker = getHeader(headers, "x-amz-delete-marker").map(Boolean::parseBoolean).orElse(false);
        final String versionId = getHeaderOrNull(headers, "x-amz-version-id");
        final String requestCharged = getHeaderOrNull(headers, "x-amz-request-charged");

        return DeleteObjectResponse.builder()
            .deleteMarker(deleteMarker)
            .versionId(versionId)
            .requestCharged(requestCharged)
            .build();
    }
}
