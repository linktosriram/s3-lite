package io.github.linktosriram.s3lite.api.response;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/v2-RESTBucketGET.html
 */
public class ListObjectsV2Response {

    private final String bucketName;

    private final List<CommonPrefix> commonPrefixes;

    private final List<S3Object> contents;

    private final String continuationToken;

    private final String delimiter;

    private final String encodingType;

    private final Boolean isTruncated;

    private final Integer keyCount;

    private final Integer maxKeys;

    private final String nextContinuationToken;

    private final String prefix;

    private final String startAfter;

    private ListObjectsV2Response(final Builder builder) {
        this.bucketName = builder.bucketName;
        this.commonPrefixes = builder.commonPrefixes;
        this.contents = builder.contents;
        this.continuationToken = builder.continuationToken;
        this.delimiter = builder.delimiter;
        this.encodingType = builder.encodingType;
        this.isTruncated = builder.isTruncated;
        this.keyCount = builder.keyCount;
        this.maxKeys = builder.maxKeys;
        this.nextContinuationToken = builder.nextContinuationToken;
        this.prefix = builder.prefix;
        this.startAfter = builder.startAfter;
    }

    public String getBucketName() {
        return bucketName;
    }

    public List<CommonPrefix> getCommonPrefixes() {
        return unmodifiableList(commonPrefixes);
    }

    public List<S3Object> getContents() {
        return contents;
    }

    public String getContinuationToken() {
        return continuationToken;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public Boolean isTruncated() {
        return isTruncated;
    }

    public Integer getKeyCount() {
        return keyCount;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public String getNextContinuationToken() {
        return nextContinuationToken;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getStartAfter() {
        return startAfter;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final ListObjectsV2Response that = (ListObjectsV2Response) obj;
        return Objects.equals(bucketName, that.bucketName) &&
            Objects.equals(commonPrefixes, that.commonPrefixes) &&
            Objects.equals(contents, that.contents) &&
            Objects.equals(continuationToken, that.continuationToken) &&
            Objects.equals(delimiter, that.delimiter) &&
            Objects.equals(encodingType, that.encodingType) &&
            Objects.equals(isTruncated, that.isTruncated) &&
            Objects.equals(keyCount, that.keyCount) &&
            Objects.equals(maxKeys, that.maxKeys) &&
            Objects.equals(nextContinuationToken, that.nextContinuationToken) &&
            Objects.equals(prefix, that.prefix) &&
            Objects.equals(startAfter, that.startAfter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketName, commonPrefixes, contents, continuationToken, delimiter, encodingType,
            isTruncated, keyCount, maxKeys, nextContinuationToken, prefix, startAfter);
    }

    @Override
    public String toString() {
        return "ListObjectsV2Response{" +
            "bucketName='" + bucketName + '\'' +
            ", commonPrefixes=" + commonPrefixes +
            ", contents=" + contents +
            ", continuationToken='" + continuationToken + '\'' +
            ", delimiter='" + delimiter + '\'' +
            ", encodingType='" + encodingType + '\'' +
            ", isTruncated=" + isTruncated +
            ", keyCount=" + keyCount +
            ", maxKeys=" + maxKeys +
            ", nextContinuationToken='" + nextContinuationToken + '\'' +
            ", prefix='" + prefix + '\'' +
            ", startAfter='" + startAfter + '\'' +
            '}';
    }

    public static class Builder {

        private String bucketName;

        private List<CommonPrefix> commonPrefixes;

        private List<S3Object> contents;

        private String continuationToken;

        private String delimiter;

        private String encodingType;

        private Boolean isTruncated;

        private Integer keyCount;

        private Integer maxKeys;

        private String nextContinuationToken;

        private String prefix;

        private String startAfter;

        private Builder() {
        }

        public Builder bucketName(final String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public Builder commonPrefixes(final List<CommonPrefix> commonPrefixes) {
            this.commonPrefixes = commonPrefixes;
            return this;
        }

        public Builder contents(final List<S3Object> contents) {
            this.contents = contents;
            return this;
        }

        public Builder continuationToken(final String continuationToken) {
            this.continuationToken = continuationToken;
            return this;
        }

        public Builder delimiter(final String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder encodingType(final String encodingType) {
            this.encodingType = encodingType;
            return this;
        }

        public Builder isTruncated(final Boolean isTruncated) {
            this.isTruncated = isTruncated;
            return this;
        }

        public Builder keyCount(final Integer keyCount) {
            this.keyCount = keyCount;
            return this;
        }

        public Builder maxKeys(final Integer maxKeys) {
            this.maxKeys = maxKeys;
            return this;
        }

        public Builder nextContinuationToken(final String nextContinuationToken) {
            this.nextContinuationToken = nextContinuationToken;
            return this;
        }

        public Builder prefix(final String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder startAfter(final String startAfter) {
            this.startAfter = startAfter;
            return this;
        }

        public ListObjectsV2Response build() {
            return new ListObjectsV2Response(this);
        }
    }
}
