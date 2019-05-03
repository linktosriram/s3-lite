package io.github.linktosriram.s3lite.http.urlconnection;

import io.github.linktosriram.s3lite.http.spi.HttpStatus;
import io.github.linktosriram.s3lite.http.spi.SdkHttpClient;
import io.github.linktosriram.s3lite.http.spi.request.ImmutableRequest;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;
import io.github.linktosriram.s3lite.http.spi.response.ImmutableResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static io.github.linktosriram.s3lite.http.spi.HttpStatus.fromStatusCode;
import static io.github.linktosriram.s3lite.http.spi.IOUtils.toByteArray;
import static io.github.linktosriram.s3lite.http.spi.SdkHttpUtils.toQueryString;
import static java.lang.String.join;

public class URLConnectionSdkHttpClient implements SdkHttpClient {

    private final Consumer<HttpsURLConnection> customizer;

    private URLConnectionSdkHttpClient(final Consumer<HttpsURLConnection> customizer) {
        this.customizer = customizer;
    }

    public static URLConnectionSdkHttpClient create() {
        return new URLConnectionSdkHttpClient((conn) -> {
        });
    }

    public static URLConnectionSdkHttpClient withCustomizer(final Consumer<HttpsURLConnection> customizer) {
        return new URLConnectionSdkHttpClient(customizer);
    }

    @Override
    public ImmutableResponse apply(final ImmutableRequest request) {
        final StringBuilder builder = new StringBuilder()
            .append(request.getEndpoint())
            .append(request.getResourcePath());

        final String queryString = toQueryString(request.getParameters());

        if (!queryString.isEmpty()) {
            builder.append("?").append(queryString);
        }

        try {
            final URL url = new URL(builder.toString());
            final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            customizer.accept(conn);
            conn.setRequestMethod(request.getHttpMethod().name());
            conn.setInstanceFollowRedirects(false);
            conn.setUseCaches(false);
            request.getHeaders()
                .forEach((name, values) -> conn.setRequestProperty(name, join(",", values)));

            request.getRequestBody()
                .map(RequestBody::getContentStreamProvider)
                .ifPresent(supplier -> {
                    final byte[] bytes;
                    try (final InputStream inputStream = supplier.get()) {
                        // TODO: Support streaming uploads
                        bytes = toByteArray(inputStream);
                    } catch (final IOException e) {
                        throw new UncheckedIOException(e);
                    }

                    conn.setDoOutput(true);
                    conn.setFixedLengthStreamingMode(bytes.length);

                    try (final OutputStream outputStream = conn.getOutputStream()) {
                        outputStream.write(bytes);
                    } catch (final IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

            final Map<String, List<String>> headers = conn.getHeaderFields();
            final int statusCode = conn.getResponseCode();
            final HttpStatus httpsStatus = fromStatusCode(statusCode);

            final InputStream inputStream = httpsStatus.is2xxSuccessful() ?
                conn.getInputStream() : conn.getErrorStream();

            return ImmutableResponse.builder()
                .status(httpsStatus)
                .headers(headers)
                .responseBody(inputStream)
                .build();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void close() {
    }
}
