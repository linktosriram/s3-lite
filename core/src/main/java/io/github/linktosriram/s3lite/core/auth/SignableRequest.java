package io.github.linktosriram.s3lite.core.auth;

import io.github.linktosriram.s3lite.http.spi.request.ImmutableRequest;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;

public interface SignableRequest extends ImmutableRequest {

    void addHeader(String name, String value);

    void setRequestBody(RequestBody requestBody);

    void addParameter(String key, String value);
}
