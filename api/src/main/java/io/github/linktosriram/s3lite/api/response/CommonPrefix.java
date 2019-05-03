package io.github.linktosriram.s3lite.api.response;

import java.util.Objects;

public class CommonPrefix {

    private final Owner owner;

    private final String prefix;

    private CommonPrefix(final Builder builder) {
        this.owner = builder.owner;
        this.prefix = builder.prefix;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getPrefix() {
        return prefix;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final CommonPrefix that = (CommonPrefix) obj;
        return Objects.equals(owner, that.owner) &&
            Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, prefix);
    }

    @Override
    public String toString() {
        return "CommonPrefix{" +
            "owner=" + owner +
            ", prefix='" + prefix + '\'' +
            '}';
    }

    public static class Builder {

        private Owner owner;

        private String prefix;

        private Builder() {
        }

        public Builder owner(final Owner owner) {
            this.owner = owner;
            return this;
        }

        public Builder prefix(final String prefix) {
            this.prefix = prefix;
            return this;
        }

        public CommonPrefix build() {
            return new CommonPrefix(this);
        }
    }
}
