package io.github.linktosriram.s3lite.api.request;

import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/v2-RESTBucketGET.html
 */
public class ListObjectsV2Request {

    private final String bucketName;

    private final String continuationToken;

    private final String delimiter;

    private final String encodingType;

    private final Boolean fetchOwner;

    private final Integer maxKeys;

    private final String prefix;

    private final String requestPayer;

    private final String startAfter;

    private ListObjectsV2Request(final Builder builder) {
        this.bucketName = builder.bucketName;
        this.continuationToken = builder.continuationToken;
        this.delimiter = builder.delimiter;
        this.encodingType = builder.encodingType;
        this.fetchOwner = builder.fetchOwner;
        this.maxKeys = builder.maxKeys;
        this.prefix = builder.prefix;
        this.requestPayer = builder.requestPayer;
        this.startAfter = builder.startAfter;
    }

    public String getBucketName() {
        return bucketName;
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

    public Boolean getFetchOwner() {
        return fetchOwner;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getRequestPayer() {
        return requestPayer;
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
        final ListObjectsV2Request that = (ListObjectsV2Request) obj;
        return Objects.equals(bucketName, that.bucketName) &&
            Objects.equals(continuationToken, that.continuationToken) &&
            Objects.equals(delimiter, that.delimiter) &&
            Objects.equals(encodingType, that.encodingType) &&
            Objects.equals(fetchOwner, that.fetchOwner) &&
            Objects.equals(maxKeys, that.maxKeys) &&
            Objects.equals(prefix, that.prefix) &&
            Objects.equals(requestPayer, that.requestPayer) &&
            Objects.equals(startAfter, that.startAfter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketName, continuationToken, delimiter, encodingType, fetchOwner, maxKeys, prefix,
            requestPayer, startAfter);
    }

    @Override
    public String toString() {
        return "ListObjectsV2Request{" +
            "bucketName='" + bucketName + '\'' +
            ", continuationToken='" + continuationToken + '\'' +
            ", delimiter='" + delimiter + '\'' +
            ", encodingType='" + encodingType + '\'' +
            ", fetchOwner=" + fetchOwner +
            ", maxKeys=" + maxKeys +
            ", prefix='" + prefix + '\'' +
            ", requestPayer='" + requestPayer + '\'' +
            ", startAfter='" + startAfter + '\'' +
            '}';
    }

    public static class Builder {

        private String bucketName;

        private String continuationToken;

        private String delimiter;

        private String encodingType;

        private Boolean fetchOwner;

        private Integer maxKeys;

        private String prefix;

        private String requestPayer;

        private String startAfter;

        private Builder() {
        }

        public Builder bucketName(final String bucketName) {
            this.bucketName = bucketName;
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

        public Builder fetchOwner(final Boolean fetchOwner) {
            this.fetchOwner = fetchOwner;
            return this;
        }

        public Builder maxKeys(final Integer maxKeys) {
            this.maxKeys = maxKeys;
            return this;
        }

        public Builder prefix(final String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder requestPayer(final String requestPayer) {
            this.requestPayer = requestPayer;
            return this;
        }

        public Builder startAfter(final String startAfter) {
            this.startAfter = startAfter;
            return this;
        }

        public ListObjectsV2Request build() {
            return new ListObjectsV2Request(this);
        }
    }
}
