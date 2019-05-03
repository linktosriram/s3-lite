package io.github.linktosriram.s3lite.api.client;

import io.github.linktosriram.s3lite.api.auth.AwsCredentialsProvider;
import io.github.linktosriram.s3lite.api.region.Region;
import io.github.linktosriram.s3lite.http.spi.SdkHttpClient;

/**
 * A builder for creating an instance of {@link S3Client}.
 */
public interface S3ClientBuilder {

    S3ClientBuilder credentialsProvider(AwsCredentialsProvider provider);

    S3ClientBuilder region(Region region);

    S3ClientBuilder httpClient(SdkHttpClient httpClient);

    S3Client build();
}
