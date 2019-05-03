package io.github.linktosriram.s3lite.core.marshal;

import io.github.linktosriram.s3lite.core.auth.SignableRequest;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Map;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

final class MarshallerUtils {

    private static final String META_PREFIX = "x-amz-meta-";

    static final DateTimeFormatter RFC_1123_DATE_TIME_FORMATTER = RFC_1123_DATE_TIME.withZone(UTC);

    static final DateTimeFormatter ISO_INSTANT_FORMATTER = ISO_INSTANT.withZone(UTC);

    private MarshallerUtils() {
        throw new AssertionError();
    }

    static void addParameterIfNotNull(final SignableRequest request, final String paramName, final String paramValue) {
        if (paramValue != null) {
            request.addParameter(paramName, paramValue);
        }
    }

    static void addParameterIfNotNull(final SignableRequest request, final String paramName, final Boolean paramValue) {
        if (paramValue != null) {
            request.addParameter(paramName, Boolean.toString(paramValue));
        }
    }

    static void addParameterIfNotNull(final SignableRequest request, final String paramName, final Integer paramValue) {
        if (paramValue != null) {
            request.addParameter(paramName, Integer.toString(paramValue));
        }
    }

    static void addHeaderIfNotNull(final SignableRequest request, final String headerName, final String headerValue) {
        if (headerValue != null) {
            request.addHeader(headerName, headerValue);
        }
    }

    static void addHeaderIfNotNull(final SignableRequest request, final String headerName, final Boolean headerValue) {
        if (headerValue != null) {
            request.addHeader(headerName, Boolean.toString(headerValue));
        }
    }

    static void addHeaderIfNotNull(final SignableRequest request, final String headerName, final Long headerValue) {
        if (headerValue != null) {
            request.addHeader(headerName, Long.toString(headerValue));
        }
    }

    static String formatIfNotNull(final TemporalAccessor temporal, final DateTimeFormatter formatter) {
        if (temporal == null) {
            return null;
        }
        return formatter.format(temporal);
    }

    static void addMetadata(final SignableRequest signableRequest, final Map<String, String> metadata) {
        metadata.forEach((key, value) -> signableRequest.addHeader(META_PREFIX + key, value));
    }
}
