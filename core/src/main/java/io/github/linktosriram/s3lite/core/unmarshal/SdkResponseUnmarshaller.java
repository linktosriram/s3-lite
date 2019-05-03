package io.github.linktosriram.s3lite.core.unmarshal;

import io.github.linktosriram.s3lite.api.response.ResponseTransformer;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Unmarshalls the response headers to a POJO.
 * For response body, please have a look at {@link ResponseTransformer}
 *
 * @param <T> the type of the POJO
 */
@FunctionalInterface
public interface SdkResponseUnmarshaller<T> extends Function<Map<String, List<String>>, T> {
}
