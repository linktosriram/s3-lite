package io.github.linktosriram.s3lite.core.auth;

import io.github.linktosriram.s3lite.api.auth.AwsCredentials;
import io.github.linktosriram.s3lite.api.region.Region;
import io.github.linktosriram.s3lite.http.spi.request.ImmutableRequest;
import io.github.linktosriram.s3lite.http.spi.request.RequestBody;
import io.github.linktosriram.s3lite.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Logger;

import static io.github.linktosriram.s3lite.core.auth.SignerConstants.AUTHORIZATION;
import static io.github.linktosriram.s3lite.core.auth.SignerConstants.AWS4_SIGNING_ALGORITHM;
import static io.github.linktosriram.s3lite.core.auth.SignerConstants.AWS4_TERMINATOR;
import static io.github.linktosriram.s3lite.core.auth.SignerConstants.HOST;
import static io.github.linktosriram.s3lite.core.auth.SignerConstants.LINE_SEPARATOR;
import static io.github.linktosriram.s3lite.core.auth.SignerConstants.X_AMZ_CONTENT_SHA256;
import static io.github.linktosriram.s3lite.core.auth.SignerConstants.X_AMZ_DATE;
import static io.github.linktosriram.s3lite.http.spi.SdkHttpUtils.toQueryString;
import static io.github.linktosriram.s3lite.http.spi.SdkHttpUtils.urlEncodeIgnoreSlashes;
import static io.github.linktosriram.s3lite.util.DigestUtils.encodeHexString;
import static io.github.linktosriram.s3lite.util.DigestUtils.hmacSha256;
import static io.github.linktosriram.s3lite.util.DigestUtils.sha256Hex;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.ZoneOffset.UTC;
import static java.util.Comparator.comparing;
import static java.util.Locale.ENGLISH;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Signer implementation that signs requests with the AWS4 signing protocol.
 */
final class Aws4Signer implements RegionAwareSigner {

    private static final Logger log = Logger.getLogger(Aws4Signer.class.getName());

    private static final String SERVICE_SIGNING_NAME = "s3";
    private static final String NO_PAYLOAD_HASH = sha256Hex("");
    private static final Locale LOCALE_ENGLISH = ENGLISH;

    private static final DateTimeFormatter DATE_ONLY = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(UTC);
    private static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").withZone(UTC);

    private Region region;

    static Aws4Signer create() {
        return new Aws4Signer();
    }

    private Aws4Signer() {
    }

    @Override
    public void sign(final SignableRequest request, final AwsCredentials credentials) {
        final Instant instant = Instant.now();
        final String payloadHash = calculatePayloadHash(request);

        request.addHeader(HOST, request.getEndpoint().getHost());
        request.addHeader(X_AMZ_DATE, ISO_8601.format(instant));
        request.addHeader(X_AMZ_CONTENT_SHA256, payloadHash);

        final String signedHeaders = getSignedHeadersString(request.getHeaders().keySet());
        final String scope = getScope(instant, region.getRegionName());

        final String canonicalRequest = createCanonicalRequest(request, signedHeaders, payloadHash);
        final String stringToSign = createStringToSign(canonicalRequest, scope, instant);
        final String signature = calculateSignature(stringToSign, credentials, region.getRegionName(), instant);
        final String authHeader = buildAuthorizationHeaders(signedHeaders, signature, credentials, scope);

        request.addHeader(AUTHORIZATION, authHeader);
    }

    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public void setRegion(final Region region) {
        this.region = region;
    }

    private static String calculatePayloadHash(final ImmutableRequest request) {
        return request.getRequestBody()
            .map(RequestBody::getContentStreamProvider)
            .map(supplier -> {
                try (final InputStream data = supplier.get()) {
                    return sha256Hex(data);
                } catch (final IOException e) {
                    throw new UncheckedIOException(e);
                }
            })
            .orElse(NO_PAYLOAD_HASH);
    }

    private static String getScope(final TemporalAccessor temporal, final String regionName) {
        return DATE_ONLY.format(temporal) + "/" + regionName + "/" + SERVICE_SIGNING_NAME + "/" + AWS4_TERMINATOR;
    }

    /**
     * Step 1: Create a Canonical Request
     * http://docs.aws.amazon.com/general/latest/gr/sigv4-create-canonical-request.html
     */
    private static String createCanonicalRequest(final ImmutableRequest request, final String signedHeaders,
                                                 final String payloadHash) {
        final String canonicalRequest = request.getHttpMethod().name() +
            LINE_SEPARATOR +
            getCanonicalizedResourcePath(request.getResourcePath()) +
            LINE_SEPARATOR +
            getCanonicalizedQueryString(request.getParameters()) +
            LINE_SEPARATOR +
            getCanonicalizedHeaderString(request.getHeaders()) +
            LINE_SEPARATOR +
            signedHeaders +
            LINE_SEPARATOR +
            payloadHash;
        log.config(() -> "AWS4 Canonical Request: " + canonicalRequest);
        return canonicalRequest;
    }

    /**
     * Step 2: Create a String to Sign
     * https://docs.aws.amazon.com/general/latest/gr/sigv4-create-string-to-sign.html
     */
    private static String createStringToSign(final String canonicalRequest, final String scope,
                                             final TemporalAccessor temporal) {
        final String stringToSign = AWS4_SIGNING_ALGORITHM +
            LINE_SEPARATOR +
            ISO_8601.format(temporal) +
            LINE_SEPARATOR +
            scope +
            LINE_SEPARATOR +
            sha256Hex(canonicalRequest);
        log.config(() -> "AWS4 String to Sign: " + stringToSign);
        return stringToSign;
    }


    /**
     * Step 3: Calculate the Signature
     * https://docs.aws.amazon.com/general/latest/gr/sigv4-calculate-signature.html
     */
    private static String calculateSignature(final String stringToSign, final AwsCredentials credentials,
                                             final String regionName, final TemporalAccessor temporal) {
        final byte[] kSecret = ("AWS4" + credentials.getAWSSecretKey()).getBytes(UTF_8);
        final byte[] kDate = hmacSha256(kSecret, DATE_ONLY.format(temporal));
        final byte[] kRegion = hmacSha256(kDate, regionName);
        final byte[] kService = hmacSha256(kRegion, SERVICE_SIGNING_NAME);
        final byte[] kSigning = hmacSha256(kService, AWS4_TERMINATOR);
        final byte[] signature = hmacSha256(kSigning, stringToSign);
        return encodeHexString(signature);
    }

    /**
     * Step 4: Add the Signature to the HTTP Request
     * https://docs.aws.amazon.com/general/latest/gr/sigv4-add-signature-to-request.html
     */
    private static String buildAuthorizationHeaders(final String signedHeaders, final String signature,
                                                    final AwsCredentials credentials, final String scope) {
        final String credential = "Credential=" + credentials.getAWSAccessKeyId() + "/" + scope;
        final String headers = "SignedHeaders=" + signedHeaders;
        final String sign = "Signature=" + signature;
        return AWS4_SIGNING_ALGORITHM + " " + credential + ", " + headers + ", " + sign;
    }

    /**
     * The canonical URI is the URI-encoded version of the absolute path component of the URI which is everything in
     * the URI from the HTTP host to the question mark character ("?") that begins the query string parameters.
     */
    private static String getCanonicalizedResourcePath(final String resourcePath) {
        return urlEncodeIgnoreSlashes(resourcePath);
    }

    /**
     * Sort the parameter names by character code point in ascending order
     * Parameters with duplicate names should be sorted by value
     */
    private static String getCanonicalizedQueryString(final Map<String, List<String>> parameters) {
        final Function<Entry<String, List<String>>, String> keyMapper = Entry::getKey;
        final Function<Entry<String, List<String>>, List<String>> valueMapper =
            entry -> entry.getValue()
                .stream()
                .sorted()
                .collect(toList());

        final Map<String, List<String>> sortedParameters = parameters.entrySet()
            .stream()
            .collect(toMap(keyMapper, valueMapper, throwingMerger(), TreeMap::new));

        return toQueryString(sortedParameters);
    }

    private static String getCanonicalizedHeaderString(final Map<String, List<String>> headers) {
        return headers.entrySet()
            .stream()
            .sorted(comparing(entry -> entry.getKey().toLowerCase(LOCALE_ENGLISH)))
            .map(entry -> {
                final String headerName = entry.getKey().toLowerCase(LOCALE_ENGLISH);
                final String headerValues = entry.getValue().stream()
                    .map(StringUtils::trimAll)
                    .collect(joining(","));
                return headerName + ":" + headerValues + LINE_SEPARATOR;
            })
            .collect(joining());
    }

    private static String getSignedHeadersString(final Collection<String> headers) {
        return headers.stream()
            .sorted(CASE_INSENSITIVE_ORDER)
            .map(header -> header.toLowerCase(LOCALE_ENGLISH))
            .collect(joining(";"));
    }

    /**
     * Returns a merge function, suitable for use in Map.merge() or toMap(), which always throws {@code
     * IllegalStateException}.  This can be used to enforce the assumption that the elements being collected are
     * distinct.
     *
     * @param <T> the type of input arguments to the merge function
     * @return a merge function which always throw {@code IllegalStateException}
     */
    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException("Duplicate key " + u);
        };
    }
}
