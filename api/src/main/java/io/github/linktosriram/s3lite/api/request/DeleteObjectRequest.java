package io.github.linktosriram.s3lite.api.request;

import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/RESTObjectDELETE.html
 */
public class DeleteObjectRequest {

    private final String bucketName;

    private final Boolean bypassGovernanceRetention;

    private final String key;

    private final String mfa;

    private final String requestPayer;

    private final String versionId;

    private DeleteObjectRequest(final Builder builder) {
        this.bucketName = builder.bucketName;
        this.bypassGovernanceRetention = builder.bypassGovernanceRetention;
        this.key = builder.key;
        this.mfa = builder.mfa;
        this.requestPayer = builder.requestPayer;
        this.versionId = builder.versionId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public Boolean getBypassGovernanceRetention() {
        return bypassGovernanceRetention;
    }

    public String getKey() {
        return key;
    }

    public String getMfa() {
        return mfa;
    }

    public String getRequestPayer() {
        return requestPayer;
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
        final DeleteObjectRequest that = (DeleteObjectRequest) obj;
        return Objects.equals(bucketName, that.bucketName) &&
            Objects.equals(bypassGovernanceRetention, that.bypassGovernanceRetention) &&
            Objects.equals(key, that.key) &&
            Objects.equals(mfa, that.mfa) &&
            Objects.equals(requestPayer, that.requestPayer) &&
            Objects.equals(versionId, that.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketName, bypassGovernanceRetention, key, mfa, requestPayer, versionId);
    }

    @Override
    public String toString() {
        return "DeleteObjectRequest{" +
            "bucketName='" + bucketName + '\'' +
            ", bypassGovernanceRetention=" + bypassGovernanceRetention +
            ", key='" + key + '\'' +
            ", mfa='" + mfa + '\'' +
            ", requestPayer='" + requestPayer + '\'' +
            ", versionId='" + versionId + '\'' +
            '}';
    }

    public static class Builder {

        private String bucketName;

        private Boolean bypassGovernanceRetention;

        private String key;

        private String mfa;

        private String requestPayer;

        private String versionId;

        private Builder() {
        }

        public Builder bucketName(final String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public Builder bypassGovernanceRetention(final Boolean bypassGovernanceRetention) {
            this.bypassGovernanceRetention = bypassGovernanceRetention;
            return this;
        }

        public Builder key(final String key) {
            this.key = key;
            return this;
        }

        public Builder mfa(final String mfa) {
            this.mfa = mfa;
            return this;
        }

        public Builder requestPayer(final String requestPayer) {
            this.requestPayer = requestPayer;
            return this;
        }

        public Builder versionId(final String versionId) {
            this.versionId = versionId;
            return this;
        }

        public DeleteObjectRequest build() {
            return new DeleteObjectRequest(this);
        }
    }
}
