package io.github.linktosriram.s3lite.core.auth;

import io.github.linktosriram.s3lite.http.spi.HttpMethod;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.unmodifiableMap;
import static java.util.Optional.ofNullable;

public class DefaultSignableRequest implements SignableRequest {

    private final HttpMethod httpMethod;

    private final URI endpoint;

    private final String resourcePath;

    private final Map<String, List<String>> parameters = new LinkedHashMap<>();

    private final Map<String, List<String>> headers = new HashMap<>();

    private RequestBody requestBody;

    public DefaultSignableRequest(final HttpMethod httpMethod, final URI endpoint, final String resourcePath) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.resourcePath = resourcePath;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public URI getEndpoint() {
        return endpoint;
    }

    @Override
    public String getResourcePath() {
        return resourcePath;
    }

    @Override
    public Map<String, List<String>> getParameters() {
        return unmodifiableMap(parameters);
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return unmodifiableMap(headers);
    }

    @Override
    public Optional<RequestBody> getRequestBody() {
        return ofNullable(requestBody);
    }

    @Override
    public void setRequestBody(final RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public void addParameter(final String key, final String value) {
        parameters.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    @Override
    public void addHeader(final String name, final String value) {
        headers.computeIfAbsent(name, n -> new ArrayList<>()).add(value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final DefaultSignableRequest that = (DefaultSignableRequest) obj;
        return httpMethod == that.httpMethod &&
            Objects.equals(endpoint, that.endpoint) &&
            Objects.equals(resourcePath, that.resourcePath) &&
            Objects.equals(parameters, that.parameters) &&
            Objects.equals(headers, that.headers) &&
            Objects.equals(requestBody, that.requestBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpMethod, endpoint, resourcePath, parameters, headers, requestBody);
    }

    @Override
    public String toString() {
        return "DefaultSignableRequest{" +
            "httpMethod=" + httpMethod +
            ", endpoint=" + endpoint +
            ", resourcePath='" + resourcePath + '\'' +
            ", parameters=" + parameters +
            ", headers=" + headers +
            ", requestBody=" + requestBody +
            '}';
    }
}
