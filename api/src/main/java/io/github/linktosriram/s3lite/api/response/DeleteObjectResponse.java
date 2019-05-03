package io.github.linktosriram.s3lite.api.response;

import java.util.Objects;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/RESTObjectDELETE.html
 */
public class DeleteObjectResponse {

    private final Boolean deleteMarker;

    private final String requestCharged;

    private final String versionId;

    private DeleteObjectResponse(final Builder builder) {
        this.deleteMarker = builder.deleteMarker;
        this.requestCharged = builder.requestCharged;
        this.versionId = builder.versionId;
    }

    public Boolean getDeleteMarker() {
        return deleteMarker;
    }

    public String getRequestCharged() {
        return requestCharged;
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
        final DeleteObjectResponse that = (DeleteObjectResponse) obj;
        return Objects.equals(deleteMarker, that.deleteMarker) &&
            Objects.equals(requestCharged, that.requestCharged) &&
            Objects.equals(versionId, that.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteMarker, requestCharged, versionId);
    }

    @Override
    public String toString() {
        return "DeleteObjectResponse{" +
            "deleteMarker=" + deleteMarker +
            ", requestCharged='" + requestCharged + '\'' +
            ", versionId='" + versionId + '\'' +
            '}';
    }

    public static class Builder {

        private Boolean deleteMarker;

        private String requestCharged;

        private String versionId;

        private Builder() {
        }

        public Builder deleteMarker(final Boolean deleteMarker) {
            this.deleteMarker = deleteMarker;
            return this;
        }

        public Builder requestCharged(final String requestCharged) {
            this.requestCharged = requestCharged;
            return this;
        }

        public Builder versionId(final String versionId) {
            this.versionId = versionId;
            return this;
        }

        public DeleteObjectResponse build() {
            return new DeleteObjectResponse(this);
        }
    }
}
