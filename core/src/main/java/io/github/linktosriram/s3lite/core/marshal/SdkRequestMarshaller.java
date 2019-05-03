package io.github.linktosriram.s3lite.core.marshal;

import io.github.linktosriram.s3lite.core.auth.SignableRequest;

import java.util.function.BiConsumer;

/**
 * Responsible for constructing the proper {@link SignableRequest} from the request.
 * Populates the values from the request into the corresponding places in the {@link SignableRequest}
 * like query parameters, headers.
 */
@FunctionalInterface
public interface SdkRequestMarshaller<T> extends BiConsumer<SignableRequest, T> {
}
