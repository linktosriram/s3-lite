package io.github.linktosriram.s3lite.http.spi;

import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.quote;
import static java.util.stream.Collectors.joining;

/**
 * A set of utilities that assist with HTTP message-related interactions.
 */
public final class SdkHttpUtils {

    private static final Pattern ENCODED_CHARACTERS_PATTERN;

    static {
        final String pattern = quote("+") + "|" + quote("*") + "|" + quote("%7E") + "|" + quote("%2F");
        ENCODED_CHARACTERS_PATTERN = compile(pattern);
    }

    private SdkHttpUtils() {
        throw new AssertionError();
    }

    /**
     * Encode a string according to RFC 3986: encoding for URI paths, query strings, etc.
     */
    public static String urlEncode(final String value) {
        return urlEncode(value, false);
    }

    /**
     * Encode a string according to RFC 3986, but ignore "/" characters. This is useful for encoding the components
     * of a path without encoding the path separators.
     */
    public static String urlEncodeIgnoreSlashes(final String value) {
        return urlEncode(value, true);
    }

    /**
     * Serializes a map to a HTTP query string. It also the URL encoding of the keys and values.
     *
     * @param rawQueryParameters the map to be serialized
     * @return the HTTP query string
     */
    public static String toQueryString(final Map<String, List<String>> rawQueryParameters) {
        return rawQueryParameters.entrySet()
            .stream()
            .map(entry -> entry.getValue()
                .stream()
                .map(value -> urlEncode(entry.getKey()) + "=" + urlEncode(value))
                .collect(joining("&")))
            .collect(joining("&"));
    }

    /**
     * Encode a string for use in the path of a URL; uses URLEncoder.encode, (which encodes a string for use in the
     * query portion of a URL), then applies some postfilters to fix things up per the RFC. Can optionally handle
     * strings which are meant to encode a path (ie include '/'es which should NOT be escaped).
     *
     * @param value         the value to encode
     * @param ignoreSlashes true if the value is intended to represent a path
     * @return the encoded value
     */
    private static String urlEncode(final String value, final boolean ignoreSlashes) {
        try {
            final String encoded = encode(value, UTF_8.name());
            final Matcher matcher = ENCODED_CHARACTERS_PATTERN.matcher(encoded);
            final StringBuffer buffer = new StringBuffer(encoded.length());

            while (matcher.find()) {
                String replacement = matcher.group(0);

                if ("+".equals(replacement)) {
                    replacement = "%20";
                } else if ("*".equals(replacement)) {
                    replacement = "%2A";
                } else if ("%7E".equals(replacement)) {
                    replacement = "~";
                } else if (ignoreSlashes && "%2F".equals(replacement)) {
                    replacement = "/";
                }

                matcher.appendReplacement(buffer, replacement);
            }

            matcher.appendTail(buffer);
            return buffer.toString();
        } catch (final UnsupportedEncodingException e) {
            // This should never happen since we use a built-in constant
            throw new UncheckedIOException(e);
        }
    }
}
