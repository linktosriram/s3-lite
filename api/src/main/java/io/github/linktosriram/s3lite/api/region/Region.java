package io.github.linktosriram.s3lite.api.region;

import java.net.URI;
import java.util.List;

import static java.net.URI.create;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Reference: <a href="https://docs.aws.amazon.com/general/latest/gr/s3.html">Service endpoints</a>
 */
public final class Region {

    // US East (N. Virginia)
    public static final Region US_EAST_1 = Region.of("us-east-1", create("https://s3.us-east-1.amazonaws.com"));
    // US East (Ohio)
    public static final Region US_EAST_2 = Region.of("us-east-2", create("https://s3.us-east-2.amazonaws.com"));
    // US West (N. California)
    public static final Region US_WEST_1 = Region.of("us-west-1", create("https://s3.us-west-1.amazonaws.com"));
    // US West (Oregon)
    public static final Region US_WEST_2 = Region.of("us-west-2", create("https://s3.us-west-2.amazonaws.com"));
    // Asia Pacific (Hong Kong)
    public static final Region AP_EAST_1 = Region.of("ap-east-1", create("https://s3.ap-east-1.amazonaws.com"));
    // Asia Pacific (Mumbai)
    public static final Region AP_SOUTH_1 = Region.of("ap-south-1", create("https://s3.ap-south-1.amazonaws.com"));
    // Asia Pacific (Tokyo)
    public static final Region AP_NORTHEAST_1 = Region.of("ap-northeast-1", create("https://s3.ap-northeast-1.amazonaws.com"));
    // Asia Pacific (Seoul)
    public static final Region AP_NORTHEAST_2 = Region.of("ap-northeast-2", create("https://s3.ap-northeast-2.amazonaws.com"));
    // Asia Pacific (Osaka)
    public static final Region AP_NORTHEAST_3 = Region.of("ap-northeast-3", create("https://s3.ap-northeast-3.amazonaws.com"));
    // Asia Pacific (Singapore)
    public static final Region AP_SOUTHEAST_1 = Region.of("ap-southeast-1", create("https://s3.ap-southeast-1.amazonaws.com"));
    // Asia Pacific (Sydney)
    public static final Region AP_SOUTHEAST_2 = Region.of("ap-southeast-2", create("https://s3.ap-southeast-2.amazonaws.com"));
    // Asia Pacific (Jakarta)
    public static final Region AP_SOUTHEAST_3 = Region.of("ap-southeast-3", create("https://s3.ap-southeast-3.amazonaws.com"));
    // Africa (Cape Town)
    public static final Region AF_SOUTH_1 = Region.of("af-south-1", create("https://s3.af-south-1.amazonaws.com"));
    // Europe (Ireland)
    public static final Region EU_WEST_1 = Region.of("eu-west-1", create("https://s3.eu-west-1.amazonaws.com"));
    // Europe (London)
    public static final Region EU_WEST_2 = Region.of("eu-west-2", create("https://s3.eu-west-2.amazonaws.com"));
    // Europe (Paris)
    public static final Region EU_WEST_3 = Region.of("eu-west-3", create("https://s3.eu-west-3.amazonaws.com"));
    // Europe (Frankfurt)
    public static final Region EU_CENTRAL_1 = Region.of("eu-central-1", create("https://s3.eu-central-1.amazonaws.com"));
    // Europe (Stockholm)
    public static final Region EU_NORTH_1 = Region.of("eu-north-1", create("https://s3.eu-north-1.amazonaws.com"));
    // Europe (Milan)
    public static final Region EU_SOUTH_1 = Region.of("eu-south-1", create("https://s3.eu-south-1.amazonaws.com"));
    // Canada (Central)
    public static final Region CA_CENTRAL_1 = Region.of("ca-central-1", create("https://s3.ca-central-1.amazonaws.com"));
    // South America (São Paulo)
    public static final Region SA_EAST_1 = Region.of("sa-east-1", create("https://s3.sa-east-1.amazonaws.com"));
    // Middle East (UAE)
    public static final Region ME_CENTRAL_1 = Region.of("me-central-1", create("https://s3.me-central-1.amazonaws.com"));
    // Middle East (Bahrain)
    public static final Region ME_SOUTH_1 = Region.of("me-south-1", create("https://s3.me-south-1.amazonaws.com"));
    // China (Beijing)
    public static final Region CN_NORTH_1 = Region.of("cn-north-1", create("https://s3.cn-north-1.amazonaws.com.cn"));
    // China (Ningxia)
    public static final Region CN_NORTHWEST_1 = Region.of("cn-northwest-1", create("https://s3.cn-northwest-1.amazonaws.com.cn"));
    // AWS GovCloud (US-East)
    public static final Region US_GOV_EAST_1 = Region.of("us-gov-east-1", create("https://s3.us-gov-east-1.amazonaws.com"));
    // AWS GovCloud (US-West)
    public static final Region US_GOV_WEST_1 = Region.of("us-gov-west-1", create("https://s3.us-gov-west-1.amazonaws.com"));

    private static final List<Region> REGIONS = unmodifiableList(asList(US_EAST_1, US_EAST_2, US_WEST_1, US_WEST_2, AP_EAST_1,
        AP_SOUTH_1, AP_NORTHEAST_1, AP_NORTHEAST_2, AP_NORTHEAST_3, AP_SOUTHEAST_1, AP_SOUTHEAST_2, AP_SOUTHEAST_3, AF_SOUTH_1,
        EU_WEST_1, EU_WEST_2, EU_WEST_3, EU_CENTRAL_1, EU_NORTH_1, EU_SOUTH_1, CA_CENTRAL_1, SA_EAST_1, ME_CENTRAL_1, ME_SOUTH_1,
        CN_NORTH_1, CN_NORTHWEST_1, US_GOV_EAST_1, US_GOV_WEST_1));

    public static Region of(final String regionName, final URI endpoint) {
        return new Region(regionName, endpoint);
    }
    public static Region of(final String regionName, final URI endpoint, final boolean usePathStyleRequests) {
        return new Region(regionName, endpoint, usePathStyleRequests);
    }

    private final String regionName;
    private final URI endpoint;
    private final boolean usePathStyleRequests;

    private Region(final String regionName, final URI endpoint, final boolean usePathStyleRequests) {
        this.regionName = regionName;
        this.endpoint = endpoint;
        this.usePathStyleRequests = usePathStyleRequests;
    }

    private Region(final String regionName, final URI endpoint) {
        this(regionName, endpoint, false);
    }

    public String getRegionName() {
        return regionName;
    }

    public URI getEndpoint() {
        return endpoint;
    }

    public boolean getUsePathStyleRequests() {
        return usePathStyleRequests;
    }

    public static Region fromString(final String regionName) {
        return REGIONS.stream()
            .filter(e -> e.regionName.equals(regionName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No matching constant for: " + regionName));
    }

    @Override
    public String toString() {
        return "Region{" +
            "regionName='" + regionName + '\'' +
            ", endpoint=" + endpoint +
            ", usePathStyleRequests=" + usePathStyleRequests +
            '}';
    }
}
