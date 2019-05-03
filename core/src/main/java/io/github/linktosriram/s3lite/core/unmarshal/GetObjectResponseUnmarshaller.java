package io.github.linktosriram.s3lite.core.unmarshal;

import io.github.linktosriram.s3lite.api.response.GetObjectResponse;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.RFC_1123_DATE_TIME_FORMATTER;
import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.getHeader;
import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.getHeaderOrNull;
import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.getMetadata;
import static io.github.linktosriram.s3lite.core.unmarshal.UnmarshallerUtils.parseDate;

public class GetObjectResponseUnmarshaller implements SdkResponseUnmarshaller<GetObjectResponse> {

    @Override
    public GetObjectResponse apply(final Map<String, List<String>> headers) {
        final String acceptRanges = getHeaderOrNull(headers, "Accept-Ranges");
        final String cacheControl = getHeaderOrNull(headers, "Cache-Control");
        final String contentDisposition = getHeaderOrNull(headers, "Content-Disposition");
        final String contentEncoding = getHeaderOrNull(headers, "Content-Encoding");
        final String contentLanguage = getHeaderOrNull(headers, "Content-Language");
        final Long contentLength = getHeader(headers, "Content-Length").map(Long::parseLong).orElse(null);
        final String contentRange = getHeaderOrNull(headers, "Content-Range");
        final String contentType = getHeaderOrNull(headers, "Content-Type");
        final Boolean deleteMarker = getHeader(headers, "x-amz-delete-marker").map(Boolean::parseBoolean).orElse(false);
        final String eTag = getHeaderOrNull(headers, "ETag");
        final String expiration = getHeaderOrNull(headers, "x-amz-expiration");
        final Instant expires =
            getHeader(headers, "Expires").map(e -> parseDate(e, RFC_1123_DATE_TIME_FORMATTER)).orElse(null);
        final Instant lastModified =
            getHeader(headers, "Last-Modified").map(e -> parseDate(e, RFC_1123_DATE_TIME_FORMATTER)).orElse(null);
        final Map<String, String> metadata = getMetadata(headers);
        final Integer missingMeta = getHeader(headers, "x-amz-missing-meta").map(Integer::parseInt).orElse(null);
        final String objectLockLegalHoldStatus = getHeaderOrNull(headers, "x-amz-object-lock-legal-hold");
        final String objectLockMode = getHeaderOrNull(headers, "x-amz-object-lock-mode");
        final Instant objectLockRetainUntilDate =
            getHeader(headers, "x-amz-object-lock-retain-until-date").map(Instant::parse).orElse(null);
        final Integer partsCount = getHeader(headers, "x-amz-mp-parts-count").map(Integer::parseInt).orElse(null);
        final String replicationStatus = getHeaderOrNull(headers, "x-amz-replication-status");
        final String requestCharged = getHeaderOrNull(headers, "x-amz-request-charged");
        final String restore = getHeaderOrNull(headers, "x-amz-restore");
        final String serverSideEncryption = getHeaderOrNull(headers, "x-amz-server-side-encryption");
        final String sseCustomerAlgorithm = getHeaderOrNull(headers, "x-amz-server-side-encryption-customer-algorithm");
        final String sseCustomerKeyMD5 = getHeaderOrNull(headers, "x-amz-server-side-encryption-customer-key-MD5");
        final String sseKmsId = getHeaderOrNull(headers, "x-amz-server-side-encryption-aws-kms-key-id");
        final String storageClass = getHeaderOrNull(headers, "x-amz-storage-class");
        final Integer tagCount = getHeader(headers, "x-amz-tagging-count").map(Integer::parseInt).orElse(null);
        final String versionId = getHeaderOrNull(headers, "x-amz-version-id");
        final String websiteRedirectLocation = getHeaderOrNull(headers, "x-amz-website-redirect-location");

        return GetObjectResponse.builder()
            .acceptRanges(acceptRanges)
            .cacheControl(cacheControl)
            .contentDisposition(contentDisposition)
            .contentEncoding(contentEncoding)
            .contentLanguage(contentLanguage)
            .contentLength(contentLength)
            .contentRange(contentRange)
            .contentType(contentType)
            .deleteMarker(deleteMarker)
            .eTag(eTag)
            .expiration(expiration)
            .expires(expires)
            .lastModified(lastModified)
            .metadata(metadata)
            .missingMeta(missingMeta)
            .objectLockLegalHoldStatus(objectLockLegalHoldStatus)
            .objectLockMode(objectLockMode)
            .objectLockRetainUntilDate(objectLockRetainUntilDate)
            .partsCount(partsCount)
            .replicationStatus(replicationStatus)
            .requestCharged(requestCharged)
            .restore(restore)
            .serverSideEncryption(serverSideEncryption)
            .sseCustomerAlgorithm(sseCustomerAlgorithm)
            .sseCustomerKeyMD5(sseCustomerKeyMD5)
            .sseKmsKeyId(sseKmsId)
            .storageClass(storageClass)
            .tagCount(tagCount)
            .versionId(versionId)
            .websiteRedirectLocation(websiteRedirectLocation)
            .build();
    }
}
