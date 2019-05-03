package io.github.linktosriram.s3lite.api.response;

import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/RESTObjectPUT.html
 */
public class PutObjectResponse {

    private final String eTag;

    private final String expiration;

    private final String requestCharged;

    private final String serverSideEncryption;

    private final String sseCustomerAlgorithm;

    private final String sseCustomerKeyMD5;

    private final String sseKmsKeyId;

    private final String versionId;

    private PutObjectResponse(final Builder builder) {
        this.eTag = builder.eTag;
        this.expiration = builder.expiration;
        this.requestCharged = builder.requestCharged;
        this.serverSideEncryption = builder.serverSideEncryption;
        this.sseCustomerAlgorithm = builder.sseCustomerAlgorithm;
        this.sseCustomerKeyMD5 = builder.sseCustomerKeyMD5;
        this.sseKmsKeyId = builder.sseKmsKeyId;
        this.versionId = builder.versionId;
    }

    public String getETag() {
        return eTag;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getRequestCharged() {
        return requestCharged;
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
        final PutObjectResponse that = (PutObjectResponse) obj;
        return Objects.equals(eTag, that.eTag) &&
            Objects.equals(expiration, that.expiration) &&
            Objects.equals(requestCharged, that.requestCharged) &&
            Objects.equals(serverSideEncryption, that.serverSideEncryption) &&
            Objects.equals(sseCustomerAlgorithm, that.sseCustomerAlgorithm) &&
            Objects.equals(sseCustomerKeyMD5, that.sseCustomerKeyMD5) &&
            Objects.equals(sseKmsKeyId, that.sseKmsKeyId) &&
            Objects.equals(versionId, that.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eTag, expiration, requestCharged, serverSideEncryption, sseCustomerAlgorithm,
            sseCustomerKeyMD5, sseKmsKeyId, versionId);
    }

    @Override
    public String toString() {
        return "PutObjectResponse{" +
            "eTag='" + eTag + '\'' +
            ", expiration='" + expiration + '\'' +
            ", requestCharged='" + requestCharged + '\'' +
            ", serverSideEncryption='" + serverSideEncryption + '\'' +
            ", sseCustomerAlgorithm='" + sseCustomerAlgorithm + '\'' +
            ", sseCustomerKeyMD5='" + sseCustomerKeyMD5 + '\'' +
            ", sseKmsKeyId='" + sseKmsKeyId + '\'' +
            ", versionId='" + versionId + '\'' +
            '}';
    }

    public static class Builder {

        private String eTag;

        private String expiration;

        private String requestCharged;

        private String serverSideEncryption;

        private String sseCustomerAlgorithm;

        private String sseCustomerKeyMD5;

        private String sseKmsKeyId;

        private String versionId;

        private Builder() {
        }

        public Builder eTag(final String eTag) {
            this.eTag = eTag;
            return this;
        }

        public Builder expiration(final String expiration) {
            this.expiration = expiration;
            return this;
        }

        public Builder requestCharged(final String requestCharged) {
            this.requestCharged = requestCharged;
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

        public Builder versionId(final String versionId) {
            this.versionId = versionId;
            return this;
        }

        public PutObjectResponse build() {
            return new PutObjectResponse(this);
        }
    }
}
