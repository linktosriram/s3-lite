package io.github.linktosriram.s3lite.http.spi;

import io.github.linktosriram.s3lite.http.spi.request.ImmutableRequest;
import io.github.linktosriram.s3lite.http.spi.response.ImmutableResponse;

import java.io.Closeable;
import java.util.function.Function;

public interface SdkHttpClient extends Function<ImmutableRequest, ImmutableResponse>, Closeable {
}
