package io.github.linktosriram.s3lite.api.request;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.unmodifiableMap;

/**
 * Reference https://docs.aws.amazon.com/AmazonS3/latest/API/RESTObjectPUT.html
 */
public class PutObjectRequest {

    private final String acl;

    private final String bucketName;

    private final String cacheControl;

    private final String contentDisposition;

    private final String contentEncoding;

    private final String contentLanguage;

    private final Long contentLength;

    private final String contentMD5;

    private final String contentType;

    private final Instant expires;

    private final String grantFullControl;

    private final String grantRead;

    private final String grantReadACP;

    private final String grantWrite;

    private final String grantWriteACP;

    private final String key;

    private final Map<String, String> metadata;

    private final String objectLockLegalHoldStatus;

    private final String objectLockMode;

    private final Instant objectLockRetainUntilDate;

    private final String requestPayer;

    private final String serverSideEncryption;

    private final String sseCustomerAlgorithm;

    private final String sseCustomerKey;

    private final String sseCustomerKeyMD5;

    private final String sseEncryptionContext;

    private final String sseKmsKeyId;

    private final String storageClass;

    private final String tagging;

    private final String websiteRedirectLocation;

    private PutObjectRequest(final Builder builder) {
        this.acl = builder.acl;
        this.bucketName = builder.bucketName;
        this.cacheControl = builder.cacheControl;
        this.contentDisposition = builder.contentDisposition;
        this.contentEncoding = builder.contentEncoding;
        this.contentLanguage = builder.contentLanguage;
        this.contentLength = builder.contentLength;
        this.contentMD5 = builder.contentMD5;
        this.contentType = builder.contentType;
        this.expires = builder.expires;
        this.grantFullControl = builder.grantFullControl;
        this.grantRead = builder.grantRead;
        this.grantReadACP = builder.grantReadACP;
        this.grantWrite = builder.grantWrite;
        this.grantWriteACP = builder.grantWriteACP;
        this.key = builder.key;
        this.metadata = builder.metadata;
        this.objectLockLegalHoldStatus = builder.objectLockLegalHoldStatus;
        this.objectLockMode = builder.objectLockMode;
        this.objectLockRetainUntilDate = builder.objectLockRetainUntilDate;
        this.requestPayer = builder.requestPayer;
        this.serverSideEncryption = builder.serverSideEncryption;
        this.sseCustomerAlgorithm = builder.sseCustomerAlgorithm;
        this.sseCustomerKey = builder.sseCustomerKey;
        this.sseCustomerKeyMD5 = builder.sseCustomerKeyMD5;
        this.sseEncryptionContext = builder.sseEncryptionContext;
        this.sseKmsKeyId = builder.sseKmsKeyId;
        this.storageClass = builder.storageClass;
        this.tagging = builder.tagging;
        this.websiteRedirectLocation = builder.websiteRedirectLocation;
    }

    public String getAcl() {
        return acl;
    }

    public String getBucketName() {
        return bucketName;
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

    public String getContentMD5() {
        return contentMD5;
    }

    public String getContentType() {
        return contentType;
    }

    public Instant getExpires() {
        return expires;
    }

    public String getGrantFullControl() {
        return grantFullControl;
    }

    public String getGrantRead() {
        return grantRead;
    }

    public String getGrantReadACP() {
        return grantReadACP;
    }

    public String getGrantWrite() {
        return grantWrite;
    }

    public String getGrantWriteACP() {
        return grantWriteACP;
    }

    public String getKey() {
        return key;
    }

    public Map<String, String> getMetadata() {
        return unmodifiableMap(metadata);
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

    public String getRequestPayer() {
        return requestPayer;
    }

    public String getServerSideEncryption() {
        return serverSideEncryption;
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

    public String getSseEncryptionContext() {
        return sseEncryptionContext;
    }

    public String getSseKmsKeyId() {
        return sseKmsKeyId;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public String getTagging() {
        return tagging;
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
        final PutObjectRequest that = (PutObjectRequest) obj;
        return Objects.equals(acl, that.acl) &&
            Objects.equals(bucketName, that.bucketName) &&
            Objects.equals(cacheControl, that.cacheControl) &&
            Objects.equals(contentDisposition, that.contentDisposition) &&
            Objects.equals(contentEncoding, that.contentEncoding) &&
            Objects.equals(contentLanguage, that.contentLanguage) &&
            Objects.equals(contentLength, that.contentLength) &&
            Objects.equals(contentMD5, that.contentMD5) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(expires, that.expires) &&
            Objects.equals(grantFullControl, that.grantFullControl) &&
            Objects.equals(grantRead, that.grantRead) &&
            Objects.equals(grantReadACP, that.grantReadACP) &&
            Objects.equals(grantWrite, that.grantWrite) &&
            Objects.equals(grantWriteACP, that.grantWriteACP) &&
            Objects.equals(key, that.key) &&
            Objects.equals(metadata, that.metadata) &&
            Objects.equals(objectLockLegalHoldStatus, that.objectLockLegalHoldStatus) &&
            Objects.equals(objectLockMode, that.objectLockMode) &&
            Objects.equals(objectLockRetainUntilDate, that.objectLockRetainUntilDate) &&
            Objects.equals(requestPayer, that.requestPayer) &&
            Objects.equals(serverSideEncryption, that.serverSideEncryption) &&
            Objects.equals(sseCustomerAlgorithm, that.sseCustomerAlgorithm) &&
            Objects.equals(sseCustomerKey, that.sseCustomerKey) &&
            Objects.equals(sseCustomerKeyMD5, that.sseCustomerKeyMD5) &&
            Objects.equals(sseEncryptionContext, that.sseEncryptionContext) &&
            Objects.equals(sseKmsKeyId, that.sseKmsKeyId) &&
            Objects.equals(storageClass, that.storageClass) &&
            Objects.equals(tagging, that.tagging) &&
            Objects.equals(websiteRedirectLocation, that.websiteRedirectLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acl, bucketName, cacheControl, contentDisposition, contentEncoding, contentLanguage,
            contentLength, contentMD5, contentType, expires, grantFullControl, grantRead, grantReadACP, grantWrite,
            grantWriteACP, key, metadata, objectLockLegalHoldStatus, objectLockMode, objectLockRetainUntilDate,
            requestPayer, serverSideEncryption, sseCustomerAlgorithm, sseCustomerKey, sseCustomerKeyMD5,
            sseEncryptionContext, sseKmsKeyId, storageClass, tagging, websiteRedirectLocation);
    }

    @Override
    public String toString() {
        return "PutObjectRequest{" +
            "acl='" + acl + '\'' +
            ", bucketName='" + bucketName + '\'' +
            ", cacheControl='" + cacheControl + '\'' +
            ", contentDisposition='" + contentDisposition + '\'' +
            ", contentEncoding='" + contentEncoding + '\'' +
            ", contentLanguage='" + contentLanguage + '\'' +
            ", contentLength=" + contentLength +
            ", contentMD5='" + contentMD5 + '\'' +
            ", contentType='" + contentType + '\'' +
            ", expires=" + expires +
            ", grantFullControl='" + grantFullControl + '\'' +
            ", grantRead='" + grantRead + '\'' +
            ", grantReadACP='" + grantReadACP + '\'' +
            ", grantWrite='" + grantWrite + '\'' +
            ", grantWriteACP='" + grantWriteACP + '\'' +
            ", key='" + key + '\'' +
            ", metadata=" + metadata +
            ", objectLockLegalHoldStatus='" + objectLockLegalHoldStatus + '\'' +
            ", objectLockMode='" + objectLockMode + '\'' +
            ", objectLockRetainUntilDate=" + objectLockRetainUntilDate +
            ", requestPayer='" + requestPayer + '\'' +
            ", serverSideEncryption='" + serverSideEncryption + '\'' +
            ", sseCustomerAlgorithm='" + sseCustomerAlgorithm + '\'' +
            ", sseCustomerKey='" + sseCustomerKey + '\'' +
            ", sseCustomerKeyMD5='" + sseCustomerKeyMD5 + '\'' +
            ", sseEncryptionContext='" + sseEncryptionContext + '\'' +
            ", sseKmsKeyId='" + sseKmsKeyId + '\'' +
            ", storageClass='" + storageClass + '\'' +
            ", tagging='" + tagging + '\'' +
            ", websiteRedirectLocation='" + websiteRedirectLocation + '\'' +
            '}';
    }

    public static class Builder {

        private String acl;

        private String bucketName;

        private String cacheControl;

        private String contentDisposition;

        private String contentEncoding;

        private String contentLanguage;

        private Long contentLength;

        private String contentMD5;

        private String contentType;

        private Instant expires;

        private String grantFullControl;

        private String grantRead;

        private String grantReadACP;

        private String grantWrite;

        private String grantWriteACP;

        private String key;

        private Map<String, String> metadata = new HashMap<>();

        private String objectLockLegalHoldStatus;

        private String objectLockMode;

        private Instant objectLockRetainUntilDate;

        private String requestPayer;

        private String serverSideEncryption;

        private String sseCustomerAlgorithm;

        private String sseCustomerKey;

        private String sseCustomerKeyMD5;

        private String sseEncryptionContext;

        private String sseKmsKeyId;

        private String storageClass;

        private String tagging;

        private String websiteRedirectLocation;

        private Builder() {
        }

        public Builder acl(final String acl) {
            this.acl = acl;
            return this;
        }

        public Builder bucketName(final String bucketName) {
            this.bucketName = bucketName;
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

        public Builder contentMD5(final String contentMD5) {
            this.contentMD5 = contentMD5;
            return this;
        }

        public Builder contentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder expires(final Instant expires) {
            this.expires = expires;
            return this;
        }

        public Builder grantFullControl(final String grantFullControl) {
            this.grantFullControl = grantFullControl;
            return this;
        }

        public Builder grantRead(final String grantRead) {
            this.grantRead = grantRead;
            return this;
        }

        public Builder grantReadACP(final String grantReadACP) {
            this.grantReadACP = grantReadACP;
            return this;
        }

        public Builder grantWrite(final String grantWrite) {
            this.grantWrite = grantWrite;
            return this;
        }

        public Builder grantWriteACP(final String grantWriteACP) {
            this.grantWriteACP = grantWriteACP;
            return this;
        }

        public Builder key(final String key) {
            this.key = key;
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

        public Builder requestPayer(final String requestPayer) {
            this.requestPayer = requestPayer;
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

        public Builder sseCustomerKey(final String sseCustomerKey) {
            this.sseCustomerKey = sseCustomerKey;
            return this;
        }

        public Builder sseCustomerKeyMD5(final String sseCustomerKeyMD5) {
            this.sseCustomerKeyMD5 = sseCustomerKeyMD5;
            return this;
        }

        public Builder sseEncryptionContext(final String sseEncryptionContext) {
            this.sseEncryptionContext = sseEncryptionContext;
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

        public Builder tagging(final String tagging) {
            this.tagging = tagging;
            return this;
        }

        public Builder websiteRedirectLocation(final String websiteRedirectLocation) {
            this.websiteRedirectLocation = websiteRedirectLocation;
            return this;
        }

        public PutObjectRequest build() {
            return new PutObjectRequest(this);
        }
    }
}
