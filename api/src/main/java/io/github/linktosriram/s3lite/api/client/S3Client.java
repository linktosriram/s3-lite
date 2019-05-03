package io.github.linktosriram.s3lite.api.client;

import io.github.linktosriram.s3lite.api.request.DeleteObjectRequest;
import io.github.linktosriram.s3lite.api.request.GetObjectRequest;
import io.github.linktosriram.s3lite.api.request.ListObjectsV2Request;
import io.github.linktosriram.s3lite.api.request.PutObjectRequest;
import io.github.linktosriram.s3lite.api.response.DeleteObjectResponse;
import io.github.linktosriram.s3lite.api.response.GetObjectResponse;
import io.github.linktosriram.s3lite.api.response.ListObjectsV2Response;
import io.github.linktosriram.s3lite.api.response.PutObjectResponse;
import io.github.linktosriram.s3lite.api.response.ResponseBytes;
import io.github.linktosriram.s3lite.api.response.ResponseInputStream;
import io.github.linktosriram.s3lite.api.response.ResponseTransformer;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;

import java.io.Closeable;
import java.nio.file.Path;

/**
 * Provides an interface for accessing the Amazon S3 web service.
 * <p>
 * Amazon S3 provides storage for the Internet, and is designed to make web-scale computing easier for developers.
 * <p>
 * The Amazon S3 Java SDK provides a simple interface that can be used to store and retrieve any amount of data, at any
 * time, from anywhere on the web. It gives any developer access to the same highly scalable, reliable, secure,
 * fast, inexpensive infrastructure that Amazon uses to run its own global network of web sites. The service aims to
 * maximize benefits of scale and to pass those benefits on to developers.
 * <p>
 * For more information about Amazon S3, please see <a href="http://aws.amazon.com/s3">http://aws.amazon.com/s3</a>
 */
public interface S3Client extends Closeable {

    default ListObjectsV2Response listObjectsV2(final ListObjectsV2Request request) {
        throw new UnsupportedOperationException();
    }

    default <T> T getObject(final GetObjectRequest request,
                            final ResponseTransformer<GetObjectResponse, T> transformer) {
        throw new UnsupportedOperationException();
    }

    default GetObjectResponse getObject(final GetObjectRequest request, final Path filePath) {
        return getObject(request, ResponseTransformer.toPath(filePath));
    }

    default ResponseInputStream<GetObjectResponse> getObject(final GetObjectRequest request) {
        return getObject(request, ResponseTransformer.toInputStream());
    }

    default ResponseBytes<GetObjectResponse> getObjectAsBytes(final GetObjectRequest request) {
        return getObject(request, ResponseTransformer.toBytes());
    }

    default DeleteObjectResponse deleteObject(final DeleteObjectRequest request) {
        throw new UnsupportedOperationException();
    }

    default PutObjectResponse putObject(final PutObjectRequest request, final RequestBody body) {
        throw new UnsupportedOperationException();
    }

    default PutObjectResponse putObject(final PutObjectRequest request, final Path filePath) {
        return putObject(request, RequestBody.fromPath(filePath));
    }
}
