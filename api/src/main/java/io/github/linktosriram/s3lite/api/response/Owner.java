package io.github.linktosriram.s3lite.api.response;

import java.util.Objects;

public class Owner {

    private final String displayName;

    private final String id;

    private Owner(final Builder builder) {
        this.displayName = builder.displayName;
        this.id = builder.id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Owner that = (Owner) obj;
        return Objects.equals(displayName, that.displayName) &&
            Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, id);
    }

    @Override
    public String toString() {
        return "Owner{" +
            "displayName='" + displayName + '\'' +
            ", id='" + id + '\'' +
            '}';
    }

    public static class Builder {

        private String displayName;

        private String id;

        private Builder() {
        }

        public Builder displayName(final String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Owner build() {
            return new Owner(this);
        }
    }
}
