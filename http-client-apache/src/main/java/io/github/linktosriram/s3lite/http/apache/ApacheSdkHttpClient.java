package io.github.linktosriram.s3lite.http.apache;

import io.github.linktosriram.s3lite.http.spi.IOUtils;
import io.github.linktosriram.s3lite.http.spi.SdkHttpClient;
import io.github.linktosriram.s3lite.http.spi.request.ImmutableRequest;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;
import io.github.linktosriram.s3lite.http.spi.response.ImmutableResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.github.linktosriram.s3lite.http.spi.HttpStatus.fromStatusCode;
import static io.github.linktosriram.s3lite.http.spi.SdkHttpUtils.toQueryString;
import static java.lang.String.join;
import static org.apache.http.impl.client.HttpClients.createDefault;

public class ApacheSdkHttpClient implements SdkHttpClient {

    // HttpClient implementations are expected to be thread safe.
    // It is recommended that the same instance of this class is reused for multiple request executions.
    private final CloseableHttpClient httpClient;

    private ApacheSdkHttpClient(final CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static ApacheSdkHttpClient defaultClient() {
        return new ApacheSdkHttpClient(createDefault());
    }

    public static ApacheSdkHttpClient customClient(final CloseableHttpClient httpClient) {
        return new ApacheSdkHttpClient(httpClient);
    }

    @Override
    public ImmutableResponse apply(final ImmutableRequest request) {
        switch (request.getHttpMethod()) {
            case GET:
                return doGet(request);
            case PUT:
                return doPut(request);
            case DELETE:
                return doDelete(request);
            default:
                throw new UnsupportedOperationException(request.getHttpMethod() + " not yet supported");
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private ImmutableResponse doGet(final ImmutableRequest request) {
        try {
            final URI uri = getUri(request);
            final HttpUriRequest httpGet = new HttpGet(uri);
            addHeaders(request.getHeaders(), httpGet);

            return retrieve(httpGet);
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private ImmutableResponse doPut(final ImmutableRequest request) {
        InputStream content = null;
        try {
            final URI uri = getUri(request);
            final HttpPut httpPut = new HttpPut(uri);
            addHeaders(request.getHeaders(), httpPut);

            RequestBody body = request.getRequestBody().orElse(null);
            if (body != null) {
                content = body.getContentStreamProvider().get();
                httpPut.setEntity(new InputStreamEntity(content, body.getContentLength()));
            }

            return retrieve(httpPut);
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(content);
        }
    }

    private ImmutableResponse doDelete(final ImmutableRequest request) {
        try {
            final URI uri = getUri(request);
            final HttpUriRequest httpDelete = new HttpDelete(uri);
            addHeaders(request.getHeaders(), httpDelete);

            return retrieve(httpDelete);
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private ImmutableResponse retrieve(final HttpUriRequest request) {
        try {
            final CloseableHttpResponse response = httpClient.execute(request);
            final HttpEntity entity = response.getEntity();
            final InputStream responseBody = entity != null ? entity.getContent() : null;
            final int statusCode = response.getStatusLine().getStatusCode();

            return ImmutableResponse.builder()
                .status(fromStatusCode(statusCode))
                .headers(fromHeaders(response.getAllHeaders()))
                .responseBody(responseBody)
                .build();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static URI getUri(final ImmutableRequest request) throws URISyntaxException {
        final StringBuilder builder = new StringBuilder()
            .append(request.getEndpoint())
            .append(request.getResourcePath());

        final String queryString = toQueryString(request.getParameters());

        if (!queryString.isEmpty()) {
            builder.append("?").append(queryString);
        }

        return URI.create(builder.toString());
    }

    private static void addHeaders(final Map<String, List<String>> headers, final HttpMessage request) {
        headers.forEach((name, value) -> {
            // apache client insists on adding content-length header itself based on HttpEntity.getContentLength
            if (!"content-length".equalsIgnoreCase(name)) {
                request.addHeader(name, join(",", value));
            }
        });
    }

    private static Map<String, List<String>> fromHeaders(final Header[] headers) {
        final Map<String, List<String>> ret = new HashMap<>();
        Stream.of(headers)
            .forEach(header -> ret.computeIfAbsent(header.getName(), x -> new ArrayList<>()).add(header.getValue()));

        return ret;
    }
}
