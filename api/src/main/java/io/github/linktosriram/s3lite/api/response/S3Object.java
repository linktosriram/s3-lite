package io.github.linktosriram.s3lite.api.response;

import java.time.Instant;
import java.util.Objects;

public class S3Object {

    private final String eTag;

    private final String key;

    private final Instant lastModified;

    private final Owner owner;

    private final Long size;

    private final String storageClass;

    private S3Object(final Builder builder) {
        this.eTag = builder.eTag;
        this.key = builder.key;
        this.lastModified = builder.lastModified;
        this.owner = builder.owner;
        this.size = builder.size;
        this.storageClass = builder.storageClass;
    }

    public String getETag() {
        return eTag;
    }

    public String getKey() {
        return key;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public Owner getOwner() {
        return owner;
    }

    public Long getSize() {
        return size;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final S3Object that = (S3Object) obj;
        return Objects.equals(eTag, that.eTag) &&
            Objects.equals(key, that.key) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(owner, that.owner) &&
            Objects.equals(size, that.size) &&
            Objects.equals(storageClass, that.storageClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eTag, key, lastModified, owner, size, storageClass);
    }

    @Override
    public String toString() {
        return "S3Object{" +
            "eTag='" + eTag + '\'' +
            ", key='" + key + '\'' +
            ", lastModified=" + lastModified +
            ", owner=" + owner +
            ", size=" + size +
            ", storageClass='" + storageClass + '\'' +
            '}';
    }

    public static class Builder {

        private String eTag;

        private String key;

        private Instant lastModified;

        private Owner owner;

        private Long size;

        private String storageClass;

        private Builder() {
        }

        public Builder eTag(final String eTag) {
            this.eTag = eTag;
            return this;
        }

        public Builder key(final String key) {
            this.key = key;
            return this;
        }

        public Builder lastModified(final Instant lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder owner(final Owner owner) {
            this.owner = owner;
            return this;
        }

        public Builder size(final Long size) {
            this.size = size;
            return this;
        }

        public Builder storageClass(final String storageClass) {
            this.storageClass = storageClass;
            return this;
        }

        public S3Object build() {
            return new S3Object(this);
        }
    }
}
