package io.github.linktosriram.s3lite.core.client;

import io.github.linktosriram.s3lite.api.auth.AwsCredentials;
import io.github.linktosriram.s3lite.api.client.S3Client;
import io.github.linktosriram.s3lite.api.exception.ErrorResponse;
import io.github.linktosriram.s3lite.api.exception.NoSuchBucketException;
import io.github.linktosriram.s3lite.api.exception.NoSuchKeyException;
import io.github.linktosriram.s3lite.api.exception.S3Exception;
import io.github.linktosriram.s3lite.api.region.Region;
import io.github.linktosriram.s3lite.api.request.DeleteObjectRequest;
import io.github.linktosriram.s3lite.api.request.GetObjectRequest;
import io.github.linktosriram.s3lite.api.request.ListObjectsV2Request;
import io.github.linktosriram.s3lite.api.request.PutObjectRequest;
import io.github.linktosriram.s3lite.api.response.DeleteObjectResponse;
import io.github.linktosriram.s3lite.api.response.GetObjectResponse;
import io.github.linktosriram.s3lite.api.response.ListObjectsV2Response;
import io.github.linktosriram.s3lite.api.response.PutObjectResponse;
import io.github.linktosriram.s3lite.api.response.ResponseTransformer;
import io.github.linktosriram.s3lite.core.auth.DefaultSignableRequest;
import io.github.linktosriram.s3lite.core.auth.RegionAwareSigner;
import io.github.linktosriram.s3lite.core.auth.SignableRequest;
import io.github.linktosriram.s3lite.core.mapper.ListObjectsV2ResponseMapper;
import io.github.linktosriram.s3lite.core.marshal.DeleteObjectRequestMarshaller;
import io.github.linktosriram.s3lite.core.marshal.GetObjectRequestMarshaller;
import io.github.linktosriram.s3lite.core.marshal.ListObjectsV2RequestMarshaller;
import io.github.linktosriram.s3lite.core.marshal.PutObjectRequestMarshaller;
import io.github.linktosriram.s3lite.core.marshal.SdkRequestMarshaller;
import io.github.linktosriram.s3lite.core.unmarshal.DeleteObjectResponseUnmarshaller;
import io.github.linktosriram.s3lite.core.unmarshal.GetObjectResponseUnmarshaller;
import io.github.linktosriram.s3lite.core.unmarshal.PutObjectResponseUnmarshaller;
import io.github.linktosriram.s3lite.core.unmarshal.SdkResponseUnmarshaller;
import io.github.linktosriram.s3lite.http.spi.HttpStatus;
import io.github.linktosriram.s3lite.http.spi.IOUtils;
import io.github.linktosriram.s3lite.http.spi.SdkHttpClient;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;
import io.github.linktosriram.s3lite.http.spi.response.ImmutableResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static io.github.linktosriram.s3lite.http.spi.HttpMethod.DELETE;
import static io.github.linktosriram.s3lite.http.spi.HttpMethod.GET;
import static io.github.linktosriram.s3lite.http.spi.HttpMethod.PUT;
import static java.lang.String.format;
import static java.net.URI.create;

final class DefaultS3Client implements S3Client {

    private static final String HOST_FORMAT = "%s://%s.%s";
    private static final String PATH_FORMAT = "%s://%s";

    private final RegionAwareSigner signer = RegionAwareSigner.create();
    private final AwsCredentials credentials;
    private final SdkHttpClient httpClient;
    private final Region region;

    DefaultS3Client(final AwsCredentials credentials, final Region region, final SdkHttpClient httpClient) {
        this.credentials = credentials;
        this.httpClient = httpClient;
        this.region = region;
        signer.setRegion(region);
    }

    @Override
    public ListObjectsV2Response listObjectsV2(final ListObjectsV2Request request) {
        final URI endpoint = getEndpoint(request.getBucketName());
        final String resourcePath = getResourcePath(request.getBucketName(), "");
        final SignableRequest signableRequest = new DefaultSignableRequest(GET, endpoint, resourcePath);
        final SdkRequestMarshaller<ListObjectsV2Request> marshaller = new ListObjectsV2RequestMarshaller();

        marshaller.accept(signableRequest, request);
        signer.sign(signableRequest, credentials);

        final ImmutableResponse response = httpClient.apply(signableRequest);

        if (response.getStatus().is2xxSuccessful()) {
            return response.getResponseBody()
                .map(inputStream -> {
                    try (final InputStream input = inputStream) {
                        return IOUtils.toByteArray(input);
                    } catch (final IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .map(bytes -> new ListObjectsV2ResponseMapper().apply(bytes))
                .orElseThrow(() -> new RuntimeException("No response body found"));
        } else {
            throw handleErrorResponse(response);
        }
    }

    @Override
    public <T> T getObject(final GetObjectRequest request,
                           final ResponseTransformer<GetObjectResponse, T> transformer) {
        final URI endpoint = getEndpoint(request.getBucketName());
        final String resourcePath = getResourcePath(request.getBucketName(), request.getKey());
        final SignableRequest signableRequest = new DefaultSignableRequest(GET, endpoint, resourcePath);
        final SdkRequestMarshaller<GetObjectRequest> marshaller = new GetObjectRequestMarshaller();
        final SdkResponseUnmarshaller<GetObjectResponse> unmarshaller = new GetObjectResponseUnmarshaller();

        marshaller.accept(signableRequest, request);
        signer.sign(signableRequest, credentials);

        final ImmutableResponse httpResponse = httpClient.apply(signableRequest);

        if (httpResponse.getStatus().is2xxSuccessful()) {
            final Map<String, List<String>> headers = httpResponse.getHeaders();
            return httpResponse.getResponseBody()
                .map(inputStream -> {
                    try {
                        final GetObjectResponse response = unmarshaller.apply(headers);
                        final T transform = transformer.transform(response, inputStream);
                        if (!transformer.needsConnectionLeftOpen()) {
                            inputStream.close();
                        }
                        return transform;
                    } catch (final IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("No response body found"));
        } else {
            throw handleErrorResponse(httpResponse);
        }
    }

    @Override
    public DeleteObjectResponse deleteObject(final DeleteObjectRequest request) {
        final URI endpoint = getEndpoint(request.getBucketName());
        final String resourcePath = getResourcePath(request.getBucketName(), request.getKey());
        final SignableRequest signableRequest = new DefaultSignableRequest(DELETE, endpoint, resourcePath);
        final SdkRequestMarshaller<DeleteObjectRequest> marshaller = new DeleteObjectRequestMarshaller();
        final SdkResponseUnmarshaller<DeleteObjectResponse> unmarshaller = new DeleteObjectResponseUnmarshaller();

        marshaller.accept(signableRequest, request);
        signer.sign(signableRequest, credentials);

        final ImmutableResponse httpResponse = httpClient.apply(signableRequest);

        if (httpResponse.getStatus().is2xxSuccessful()) {
            final Map<String, List<String>> headers = httpResponse.getHeaders();
            httpResponse.getResponseBody().ifPresent(IOUtils::closeQuietly);
            return unmarshaller.apply(headers);
        } else {
            throw handleErrorResponse(httpResponse);
        }
    }

    @Override
    public PutObjectResponse putObject(final PutObjectRequest request, final RequestBody body) {
        final URI endpoint = getEndpoint(request.getBucketName());
        final String resourcePath = getResourcePath(request.getBucketName(), request.getKey());
        final SignableRequest signableRequest = new DefaultSignableRequest(PUT, endpoint, resourcePath);
        final SdkRequestMarshaller<PutObjectRequest> marshaller = new PutObjectRequestMarshaller();
        final SdkResponseUnmarshaller<PutObjectResponse> unmarshaller = new PutObjectResponseUnmarshaller();

        signableRequest.setRequestBody(body);
        marshaller.accept(signableRequest, request);
        signer.sign(signableRequest, credentials);

        final ImmutableResponse httpResponse = httpClient.apply(signableRequest);

        if (httpResponse.getStatus().is2xxSuccessful()) {
            final Map<String, List<String>> headers = httpResponse.getHeaders();
            httpResponse.getResponseBody().ifPresent(IOUtils::closeQuietly);
            return unmarshaller.apply(headers);
        } else {
            throw handleErrorResponse(httpResponse);
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private URI getEndpoint(final String bucketName) {
        URI base = region.getEndpoint();
        String endpoint;
        if (region.getUsePathStyleRequests()) {
            endpoint = format(PATH_FORMAT, base.getScheme(), base.getHost());
        } else {
            endpoint = format(HOST_FORMAT, base.getScheme(), bucketName, base.getHost());
        }

        int port = base.getPort();
        if (port > 0) {
            endpoint = endpoint + ":" + port;
        }

        return create(endpoint);
    }

    private String getResourcePath(final String bucketName, final String key) {
        if (!region.getUsePathStyleRequests()) {
            return "/" + key;
        }
        String path = region.getEndpoint().getPath();
        if (path == null) {
            path = "";
        }
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        return path + bucketName + "/" + key;
    }

    private static S3Exception handleErrorResponse(final ImmutableResponse httpResponse) {
        final HttpStatus status = httpResponse.getStatus();
        if (status.is3xxRedirection()) {
            httpResponse.getResponseBody().ifPresent(IOUtils::closeQuietly);
            final ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Service: S3, Status Code: " + status.getStatusCode());
            return new S3Exception(errorResponse);
        } else {
            return httpResponse.getResponseBody()
                .map(inputStream -> {
                    try (final InputStream input = inputStream) {
                        final ErrorResponse errorResponse = (ErrorResponse) JAXBContext.newInstance(ErrorResponse.class)
                            .createUnmarshaller()
                            .unmarshal(input);

                        switch (errorResponse.getCode()) {
                            case "NoSuchBucket":
                                return new NoSuchBucketException(errorResponse);
                            case "NoSuchKey":
                                return new NoSuchKeyException(errorResponse);
                            default:
                                return new S3Exception(errorResponse);
                        }
                    } catch (final JAXBException e) {
                        throw new RuntimeException(e);
                    } catch (final IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("Service: S3, Status Code: " + status.getStatusCode()));
        }
    }
}
