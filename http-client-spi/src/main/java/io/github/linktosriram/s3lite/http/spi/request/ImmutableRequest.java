package io.github.linktosriram.s3lite.http.spi.request;

import io.github.linktosriram.s3lite.http.spi.HttpMethod;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ImmutableRequest {

    HttpMethod getHttpMethod();

    URI getEndpoint();

    String getResourcePath();

    Map<String, List<String>> getParameters();

    Map<String, List<String>> getHeaders();

    Optional<RequestBody> getRequestBody();
}
