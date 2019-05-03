package io.github.linktosriram.s3lite.api.request;

import java.time.Instant;
import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/RESTObjectGET.html
 */
public class GetObjectRequest {

    private final String bucketName;

    private final Instant ifModifiedSince;

    private final Instant ifUnmodifiedSince;

    private final String ifMatch;

    private final String ifNoneMatch;

    private final String key;

    private final Integer partNumber;

    private final String range;

    private final String requestPayer;

    private final String responseCacheControl;

    private final String responseContentDisposition;

    private final String responseContentEncoding;

    private final String responseContentLanguage;

    private final String responseContentType;

    private final Instant responseExpires;

    private final String sseCustomerAlgorithm;

    private final String sseCustomerKey;

    private final String sseCustomerKeyMD5;

    private final String versionId;

    private GetObjectRequest(final Builder builder) {
        this.bucketName = builder.bucketName;
        this.ifModifiedSince = builder.ifModifiedSince;
        this.ifUnmodifiedSince = builder.ifUnmodifiedSince;
        this.ifMatch = builder.ifMatch;
        this.ifNoneMatch = builder.ifNoneMatch;
        this.key = builder.key;
        this.partNumber = builder.partNumber;
        this.range = builder.range;
        this.requestPayer = builder.requestPayer;
        this.responseCacheControl = builder.responseCacheControl;
        this.responseContentDisposition = builder.responseContentDisposition;
        this.responseContentEncoding = builder.responseContentEncoding;
        this.responseContentLanguage = builder.responseContentLanguage;
        this.responseContentType = builder.responseContentType;
        this.responseExpires = builder.responseExpires;
        this.sseCustomerAlgorithm = builder.sseCustomerAlgorithm;
        this.sseCustomerKey = builder.sseCustomerKey;
        this.sseCustomerKeyMD5 = builder.sseCustomerKeyMD5;
        this.versionId = builder.versionId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public Instant getIfModifiedSince() {
        return ifModifiedSince;
    }

    public Instant getIfUnmodifiedSince() {
        return ifUnmodifiedSince;
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public String getIfNoneMatch() {
        return ifNoneMatch;
    }

    public String getKey() {
        return key;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public String getRange() {
        return range;
    }

    public String getRequestPayer() {
        return requestPayer;
    }

    public String getResponseCacheControl() {
        return responseCacheControl;
    }

    public String getResponseContentDisposition() {
        return responseContentDisposition;
    }

    public String getResponseContentEncoding() {
        return responseContentEncoding;
    }

    public String getResponseContentLanguage() {
        return responseContentLanguage;
    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public Instant getResponseExpires() {
        return responseExpires;
    }

    public String getSseCustomerAlgorithm() {
        return sseCustomerAlgorithm;
    }

    public String getSseCustomerKey() {
        return sseCustomerKey;
    }

    public String getSseCustomerKeyMD5() {
        return sseCustomerKeyMD5;
    }

    public String getVersionId() {
        return versionId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final GetObjectRequest that = (GetObjectRequest) obj;
        return Objects.equals(bucketName, that.bucketName) &&
            Objects.equals(ifModifiedSince, that.ifModifiedSince) &&
            Objects.equals(ifUnmodifiedSince, that.ifUnmodifiedSince) &&
            Objects.equals(ifMatch, that.ifMatch) &&
            Objects.equals(ifNoneMatch, that.ifNoneMatch) &&
            Objects.equals(key, that.key) &&
            Objects.equals(partNumber, that.partNumber) &&
            Objects.equals(range, that.range) &&
            Objects.equals(requestPayer, that.requestPayer) &&
            Objects.equals(responseCacheControl, that.responseCacheControl) &&
            Objects.equals(responseContentDisposition, that.responseContentDisposition) &&
            Objects.equals(responseContentEncoding, that.responseContentEncoding) &&
            Objects.equals(responseContentLanguage, that.responseContentLanguage) &&
            Objects.equals(responseContentType, that.responseContentType) &&
            Objects.equals(responseExpires, that.responseExpires) &&
            Objects.equals(sseCustomerAlgorithm, that.sseCustomerAlgorithm) &&
            Objects.equals(sseCustomerKey, that.sseCustomerKey) &&
            Objects.equals(sseCustomerKeyMD5, that.sseCustomerKeyMD5) &&
            Objects.equals(versionId, that.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketName, ifModifiedSince, ifUnmodifiedSince, ifMatch, ifNoneMatch, key, partNumber,
            range, requestPayer, responseCacheControl, responseContentDisposition, responseContentEncoding,
            responseContentLanguage, responseContentType, responseExpires, sseCustomerAlgorithm, sseCustomerKey,
            sseCustomerKeyMD5, versionId);
    }

    @Override
    public String toString() {
        return "GetObjectRequest{" +
            "bucketName='" + bucketName + '\'' +
            ", ifModifiedSince=" + ifModifiedSince +
            ", ifUnmodifiedSince=" + ifUnmodifiedSince +
            ", ifMatch='" + ifMatch + '\'' +
            ", ifNoneMatch='" + ifNoneMatch + '\'' +
            ", key='" + key + '\'' +
            ", partNumber=" + partNumber +
            ", range='" + range + '\'' +
            ", requestPayer='" + requestPayer + '\'' +
            ", responseCacheControl='" + responseCacheControl + '\'' +
            ", responseContentDisposition='" + responseContentDisposition + '\'' +
            ", responseContentEncoding='" + responseContentEncoding + '\'' +
            ", responseContentLanguage='" + responseContentLanguage + '\'' +
            ", responseContentType='" + responseContentType + '\'' +
            ", responseExpires=" + responseExpires +
            ", sseCustomerAlgorithm='" + sseCustomerAlgorithm + '\'' +
            ", sseCustomerKey='" + sseCustomerKey + '\'' +
            ", sseCustomerKeyMD5='" + sseCustomerKeyMD5 + '\'' +
            ", versionId='" + versionId + '\'' +
            '}';
    }

    public static class Builder {

        private String bucketName;

        private Instant ifModifiedSince;

        private Instant ifUnmodifiedSince;

        private String ifMatch;

        private String ifNoneMatch;

        private String key;

        private Integer partNumber;

        private String range;

        private String requestPayer;

        private String responseCacheControl;

        private String responseContentDisposition;

        private String responseContentEncoding;

        private String responseContentLanguage;

        private String responseContentType;

        private Instant responseExpires;

        private String sseCustomerAlgorithm;

        private String sseCustomerKey;

        private String sseCustomerKeyMD5;

        private String versionId;

        private Builder() {
        }

        public Builder bucketName(final String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public Builder ifModifiedSince(final Instant ifModifiedSince) {
            this.ifModifiedSince = ifModifiedSince;
            return this;
        }

        public Builder ifUnmodifiedSince(final Instant ifUnmodifiedSince) {
            this.ifUnmodifiedSince = ifUnmodifiedSince;
            return this;
        }

        public Builder ifMatch(final String ifMatch) {
            this.ifMatch = ifMatch;
            return this;
        }

        public Builder ifNoneMatch(final String ifNoneMatch) {
            this.ifNoneMatch = ifNoneMatch;
            return this;
        }

        public Builder key(final String key) {
            this.key = key;
            return this;
        }

        public Builder partNumber(final Integer partNumber) {
            this.partNumber = partNumber;
            return this;
        }

        public Builder range(final String range) {
            this.range = range;
            return this;
        }

        public Builder requestPayer(final String requestPayer) {
            this.requestPayer = requestPayer;
            return this;
        }

        public Builder responseCacheControl(final String responseCacheControl) {
            this.responseCacheControl = responseCacheControl;
            return this;
        }

        public Builder responseContentDisposition(final String responseContentDisposition) {
            this.responseContentDisposition = responseContentDisposition;
            return this;
        }

        public Builder responseContentEncoding(final String responseContentEncoding) {
            this.responseContentEncoding = responseContentEncoding;
            return this;
        }

        public Builder responseContentLanguage(final String responseContentLanguage) {
            this.responseContentLanguage = responseContentLanguage;
            return this;
        }

        public Builder responseContentType(final String responseContentType) {
            this.responseContentType = responseContentType;
            return this;
        }

        public Builder responseExpires(final Instant responseExpires) {
            this.responseExpires = responseExpires;
            return this;
        }

        public Builder sseCustomerAlgorithm(final String sseCustomerAlgorithm) {
            this.sseCustomerAlgorithm = sseCustomerAlgorithm;
            return this;
        }

        public Builder sseCustomerKey(final String sseCustomerKey) {
            this.sseCustomerKey = sseCustomerKey;
            return this;
        }

        public Builder sseCustomerKeyMD5(final String sseCustomerKeyMD5) {
            this.sseCustomerKeyMD5 = sseCustomerKeyMD5;
            return this;
        }

        public Builder versionId(final String versionId) {
            this.versionId = versionId;
            return this;
        }

        public GetObjectRequest build() {
            return new GetObjectRequest(this);
        }
    }
}
