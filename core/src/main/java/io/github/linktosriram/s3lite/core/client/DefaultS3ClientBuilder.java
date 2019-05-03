package io.github.linktosriram.s3lite.core.client;

import io.github.linktosriram.s3lite.api.auth.AwsCredentialsProvider;
import io.github.linktosriram.s3lite.api.client.S3Client;
import io.github.linktosriram.s3lite.api.client.S3ClientBuilder;
import io.github.linktosriram.s3lite.api.region.Region;
import io.github.linktosriram.s3lite.http.spi.SdkHttpClient;

public class DefaultS3ClientBuilder implements S3ClientBuilder {

    private AwsCredentialsProvider provider;
    private Region region;
    private SdkHttpClient httpClient;

    @Override
    public S3ClientBuilder credentialsProvider(final AwsCredentialsProvider provider) {
        this.provider = provider;
        return this;
    }

    @Override
    public S3ClientBuilder region(final Region region) {
        this.region = region;
        return this;
    }

    @Override
    public S3ClientBuilder httpClient(final SdkHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    @Override
    public S3Client build() {
        return new DefaultS3Client(provider.get(), region, httpClient);
    }
}
