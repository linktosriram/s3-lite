package io.github.linktosriram.s3lite.api.response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/RESTObjectGET.html
 */
public class GetObjectResponse {

    private final String acceptRanges;

    private final String cacheControl;

    private final String contentDisposition;

    private final String contentEncoding;

    private final String contentLanguage;

    private final Long contentLength;

    private final String contentRange;

    private final String contentType;

    private final Boolean deleteMarker;

    private final String eTag;

    private final String expiration;

    private final Instant expires;

    private final Instant lastModified;

    private final Map<String, String> metadata;

    private final Integer missingMeta;

    private final String objectLockLegalHoldStatus;

    private final String objectLockMode;

    private final Instant objectLockRetainUntilDate;

    private final Integer partsCount;

    private final String replicationStatus;

    private final String requestCharged;

    private final String restore;

    private final String serverSideEncryption;

    private final String sseCustomerAlgorithm;

    private final String sseCustomerKeyMD5;

    private final String sseKmsKeyId;

    private final String storageClass;

    private final Integer tagCount;

    private final String versionId;

    private final String websiteRedirectLocation;

    private GetObjectResponse(final Builder builder) {
        this.acceptRanges = builder.acceptRanges;
        this.cacheControl = builder.cacheControl;
        this.contentDisposition = builder.contentDisposition;
        this.contentEncoding = builder.contentEncoding;
        this.contentLanguage = builder.contentLanguage;
        this.contentLength = builder.contentLength;
        this.contentRange = builder.contentRange;
        this.contentType = builder.contentType;
        this.deleteMarker = builder.deleteMarker;
        this.eTag = builder.eTag;
        this.expiration = builder.expiration;
        this.expires = builder.expires;
        this.lastModified = builder.lastModified;
        this.metadata = builder.metadata;
        this.missingMeta = builder.missingMeta;
        this.objectLockLegalHoldStatus = builder.objectLockLegalHoldStatus;
        this.objectLockMode = builder.objectLockMode;
        this.objectLockRetainUntilDate = builder.objectLockRetainUntilDate;
        this.partsCount = builder.partsCount;
        this.replicationStatus = builder.replicationStatus;
        this.requestCharged = builder.requestCharged;
        this.restore = builder.restore;
        this.serverSideEncryption = builder.serverSideEncryption;
        this.sseCustomerAlgorithm = builder.sseCustomerAlgorithm;
        this.sseCustomerKeyMD5 = builder.sseCustomerKeyMD5;
        this.sseKmsKeyId = builder.sseKmsKeyId;
        this.storageClass = builder.storageClass;
        this.tagCount = builder.tagCount;
        this.versionId = builder.versionId;
        this.websiteRedirectLocation = builder.websiteRedirectLocation;
    }

    public String getAcceptRanges() {
        return acceptRanges;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public String getContentRange() {
        return contentRange;
    }

    public String getContentType() {
        return contentType;
    }

    public Boolean getDeleteMarker() {
        return deleteMarker;
    }

    public String geteTag() {
        return eTag;
    }

    public String getExpiration() {
        return expiration;
    }

    public Instant getExpires() {
        return expires;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public Integer getMissingMeta() {
        return missingMeta;
    }

    public String getObjectLockLegalHoldStatus() {
        return objectLockLegalHoldStatus;
    }

    public String getObjectLockMode() {
        return objectLockMode;
    }

    public Instant getObjectLockRetainUntilDate() {
        return objectLockRetainUntilDate;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public String getReplicationStatus() {
        return replicationStatus;
    }

    public String getRequestCharged() {
        return requestCharged;
    }

    public String getRestore() {
        return restore;
    }

    public String getServerSideEncryption() {
        return serverSideEncryption;
    }

    public String getSseCustomerAlgorithm() {
        return sseCustomerAlgorithm;
    }

    public String getSseCustomerKeyMD5() {
        return sseCustomerKeyMD5;
    }

    public String getSseKmsKeyId() {
        return sseKmsKeyId;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public Integer getTagCount() {
        return tagCount;
    }

    public String getVersionId() {
        return versionId;
    }

    public String getWebsiteRedirectLocation() {
        return websiteRedirectLocation;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final GetObjectResponse that = (GetObjectResponse) obj;
        return Objects.equals(acceptRanges, that.acceptRanges) &&
            Objects.equals(cacheControl, that.cacheControl) &&
            Objects.equals(contentDisposition, that.contentDisposition) &&
            Objects.equals(contentEncoding, that.contentEncoding) &&
            Objects.equals(contentLanguage, that.contentLanguage) &&
            Objects.equals(contentLength, that.contentLength) &&
            Objects.equals(contentRange, that.contentRange) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(deleteMarker, that.deleteMarker) &&
            Objects.equals(eTag, that.eTag) &&
            Objects.equals(expiration, that.expiration) &&
            Objects.equals(expires, that.expires) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(metadata, that.metadata) &&
            Objects.equals(missingMeta, that.missingMeta) &&
            Objects.equals(objectLockLegalHoldStatus, that.objectLockLegalHoldStatus) &&
            Objects.equals(objectLockMode, that.objectLockMode) &&
            Objects.equals(objectLockRetainUntilDate, that.objectLockRetainUntilDate) &&
            Objects.equals(partsCount, that.partsCount) &&
            Objects.equals(replicationStatus, that.replicationStatus) &&
            Objects.equals(requestCharged, that.requestCharged) &&
            Objects.equals(restore, that.restore) &&
            Objects.equals(serverSideEncryption, that.serverSideEncryption) &&
            Objects.equals(sseCustomerAlgorithm, that.sseCustomerAlgorithm) &&
            Objects.equals(sseCustomerKeyMD5, that.sseCustomerKeyMD5) &&
            Objects.equals(sseKmsKeyId, that.sseKmsKeyId) &&
            Objects.equals(storageClass, that.storageClass) &&
            Objects.equals(tagCount, that.tagCount) &&
            Objects.equals(versionId, that.versionId) &&
            Objects.equals(websiteRedirectLocation, that.websiteRedirectLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptRanges, cacheControl, contentDisposition, contentEncoding, contentLanguage,
            contentLength, contentRange, contentType, deleteMarker, eTag, expiration, expires, lastModified,
            metadata, missingMeta, objectLockLegalHoldStatus, objectLockMode, objectLockRetainUntilDate, partsCount,
            replicationStatus, requestCharged, restore, serverSideEncryption, sseCustomerAlgorithm, sseCustomerKeyMD5,
            sseKmsKeyId, storageClass, tagCount, versionId, websiteRedirectLocation);
    }

    @Override
    public String toString() {
        return "GetObjectResponse{" +
            "acceptRanges='" + acceptRanges + '\'' +
            ", cacheControl='" + cacheControl + '\'' +
            ", contentDisposition='" + contentDisposition + '\'' +
            ", contentEncoding='" + contentEncoding + '\'' +
            ", contentLanguage='" + contentLanguage + '\'' +
            ", contentLength=" + contentLength +
            ", contentRange='" + contentRange + '\'' +
            ", contentType='" + contentType + '\'' +
            ", deleteMarker=" + deleteMarker +
            ", eTag='" + eTag + '\'' +
            ", expiration='" + expiration + '\'' +
            ", expires=" + expires +
            ", lastModified=" + lastModified +
            ", metadata=" + metadata +
            ", missingMeta=" + missingMeta +
            ", objectLockLegalHoldStatus='" + objectLockLegalHoldStatus + '\'' +
            ", objectLockMode='" + objectLockMode + '\'' +
            ", objectLockRetainUntilDate=" + objectLockRetainUntilDate +
            ", partsCount=" + partsCount +
            ", replicationStatus='" + replicationStatus + '\'' +
            ", requestCharged='" + requestCharged + '\'' +
            ", restore='" + restore + '\'' +
            ", serverSideEncryption='" + serverSideEncryption + '\'' +
            ", sseCustomerAlgorithm='" + sseCustomerAlgorithm + '\'' +
            ", sseCustomerKeyMD5='" + sseCustomerKeyMD5 + '\'' +
            ", sseKmsKeyId='" + sseKmsKeyId + '\'' +
            ", storageClass='" + storageClass + '\'' +
            ", tagCount=" + tagCount +
            ", versionId='" + versionId + '\'' +
            ", websiteRedirectLocation='" + websiteRedirectLocation + '\'' +
            '}';
    }

    public static class Builder {

        private String acceptRanges;

        private String cacheControl;

        private String contentDisposition;

        private String contentEncoding;

        private String contentLanguage;

        private Long contentLength;

        private String contentRange;

        private String contentType;

        private Boolean deleteMarker;

        private String eTag;

        private String expiration;

        private Instant expires;

        private Instant lastModified;

        private Map<String, String> metadata = new HashMap<>();

        private Integer missingMeta;

        private String objectLockLegalHoldStatus;

        private String objectLockMode;

        private Instant objectLockRetainUntilDate;

        private Integer partsCount;

        private String replicationStatus;

        private String requestCharged;

        private String restore;

        private String serverSideEncryption;

        private String sseCustomerAlgorithm;

        private String sseCustomerKeyMD5;

        private String sseKmsKeyId;

        private String storageClass;

        private Integer tagCount;

        private String versionId;

        private String websiteRedirectLocation;

        private Builder() {
        }

        public Builder acceptRanges(final String acceptRanges) {
            this.acceptRanges = acceptRanges;
            return this;
        }

        public Builder cacheControl(final String cacheControl) {
            this.cacheControl = cacheControl;
            return this;
        }

        public Builder contentDisposition(final String contentDisposition) {
            this.contentDisposition = contentDisposition;
            return this;
        }

        public Builder contentEncoding(final String contentEncoding) {
            this.contentEncoding = contentEncoding;
            return this;
        }

        public Builder contentLanguage(final String contentLanguage) {
            this.contentLanguage = contentLanguage;
            return this;
        }

        public Builder contentLength(final Long contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public Builder contentRange(final String contentRange) {
            this.contentRange = contentRange;
            return this;
        }

        public Builder contentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder deleteMarker(final Boolean deleteMarker) {
            this.deleteMarker = deleteMarker;
            return this;
        }

        public Builder eTag(final String eTag) {
            this.eTag = eTag;
            return this;
        }

        public Builder expiration(final String expiration) {
            this.expiration = expiration;
            return this;
        }

        public Builder expires(final Instant expires) {
            this.expires = expires;
            return this;
        }

        public Builder lastModified(final Instant lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder metadata(final Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder addMetadata(final String key, final String value) {
            this.metadata.put(key, value);
            return this;
        }

        public Builder missingMeta(final Integer missingMeta) {
            this.missingMeta = missingMeta;
            return this;
        }

        public Builder objectLockLegalHoldStatus(final String objectLockLegalHoldStatus) {
            this.objectLockLegalHoldStatus = objectLockLegalHoldStatus;
            return this;
        }

        public Builder objectLockMode(final String objectLockMode) {
            this.objectLockMode = objectLockMode;
            return this;
        }

        public Builder objectLockRetainUntilDate(final Instant objectLockRetainUntilDate) {
            this.objectLockRetainUntilDate = objectLockRetainUntilDate;
            return this;
        }

        public Builder partsCount(final Integer partsCount) {
            this.partsCount = partsCount;
            return this;
        }

        public Builder replicationStatus(final String replicationStatus) {
            this.replicationStatus = replicationStatus;
            return this;
        }

        public Builder requestCharged(final String requestCharged) {
            this.requestCharged = requestCharged;
            return this;
        }

        public Builder restore(final String restore) {
            this.restore = restore;
            return this;
        }

        public Builder serverSideEncryption(final String serverSideEncryption) {
            this.serverSideEncryption = serverSideEncryption;
            return this;
        }

        public Builder sseCustomerAlgorithm(final String sseCustomerAlgorithm) {
            this.sseCustomerAlgorithm = sseCustomerAlgorithm;
            return this;
        }

        public Builder sseCustomerKeyMD5(final String sseCustomerKeyMD5) {
            this.sseCustomerKeyMD5 = sseCustomerKeyMD5;
            return this;
        }

        public Builder sseKmsKeyId(final String sseKmsKeyId) {
            this.sseKmsKeyId = sseKmsKeyId;
            return this;
        }

        public Builder storageClass(final String storageClass) {
            this.storageClass = storageClass;
            return this;
        }

        public Builder tagCount(final Integer tagCount) {
            this.tagCount = tagCount;
            return this;
        }

        public Builder versionId(final String versionId) {
            this.versionId = versionId;
            return this;
        }

        public Builder websiteRedirectLocation(final String websiteRedirectLocation) {
            this.websiteRedirectLocation = websiteRedirectLocation;
            return this;
        }

        public GetObjectResponse build() {
            return new GetObjectResponse(this);
        }
    }
}
