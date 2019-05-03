package io.github.linktosriram.s3lite.core.auth;

import io.github.linktosriram.s3lite.api.auth.AwsCredentials;

import java.util.Objects;

public class AwsBasicCredentials implements AwsCredentials {

    private final String accessKey;
    private final String secretKey;

    private AwsBasicCredentials(final String accessKey, final String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public static AwsBasicCredentials create(final String accessKey, final String secretKey) {
        return new AwsBasicCredentials(accessKey, secretKey);
    }

    @Override
    public String getAWSAccessKeyId() {
        return accessKey;
    }

    @Override
    public String getAWSSecretKey() {
        return secretKey;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final AwsBasicCredentials that = (AwsBasicCredentials) obj;
        return Objects.equals(accessKey, that.accessKey) &&
            Objects.equals(secretKey, that.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessKey, secretKey);
    }

    @Override
    public String toString() {
        return "AwsBasicCredentials{" +
            "accessKey='" + accessKey + '\'' +
            '}';
    }
}
