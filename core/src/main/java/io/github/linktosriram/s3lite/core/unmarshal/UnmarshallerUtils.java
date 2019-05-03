package io.github.linktosriram.s3lite.core.unmarshal;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.join;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;

final class UnmarshallerUtils {

    private static final String META_PREFIX = "x-amz-meta-";
    private static final int META_PREFIX_LENGTH = META_PREFIX.length();

    static final DateTimeFormatter RFC_1123_DATE_TIME_FORMATTER = RFC_1123_DATE_TIME.withZone(UTC);

    private UnmarshallerUtils() {
        throw new AssertionError();
    }

    static Optional<String> getHeader(final Map<String, List<String>> responseHeaders, final String headerName) {
        return ofNullable(responseHeaders.get(headerName)).map(values -> join(",", values));
    }

    static String getHeaderOrNull(final Map<String, List<String>> responseHeaders, final String headerName) {
        return getHeader(responseHeaders, headerName).orElse(null);
    }

    static Instant parseDate(final CharSequence source, final DateTimeFormatter formatter) {
        return formatter.parse(source, Instant::from);
    }

    static Map<String, String> getMetadata(final Map<String, List<String>> headers) {
        final Function<Entry<String, List<String>>, String> keyMapper =
            entry -> entry.getKey().substring(META_PREFIX_LENGTH);

        final Function<Entry<String, List<String>>, String> valueMapper =
            entry -> join(",", entry.getValue());

        return headers.entrySet()
            .stream()
            .filter(entry -> entry.getKey() != null)
            .filter(entry -> entry.getKey().startsWith(META_PREFIX))
            .collect(toMap(keyMapper, valueMapper));
    }
}
