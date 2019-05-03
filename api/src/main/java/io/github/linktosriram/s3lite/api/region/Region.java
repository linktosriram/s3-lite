package io.github.linktosriram.s3lite.api.region;

import java.net.URI;
import java.util.stream.Stream;

import static java.net.URI.create;

/**
 * Reference: https://docs.aws.amazon.com/general/latest/gr/rande.html#s3_region
 */
public enum Region {

    US_EAST_1("us-east-1", create("https://s3.us-east-1.amazonaws.com")),
    US_EAST_2("us-east-2", create("https://s3.us-east-2.amazonaws.com")),
    US_WEST_1("us-west-1", create("https://s3.us-west-1.amazonaws.com")),
    US_WEST_2("us-west-2", create("https://s3.us-west-2.amazonaws.com")),

    AP_EAST("ap-east", create("https://s3.ap-east-1.amazonaws.com")),
    AP_SOUTH_1("ap-south-1", create("https://s3.ap-south-1.amazonaws.com")),
    AP_NORTHEAST_1("ap-northeast-1", create("https://s3.ap-northeast-1.amazonaws.com")),
    AP_NORTHEAST_2("ap-northeast-2", create("https://s3.ap-northeast-2.amazonaws.com")),
    AP_NORTHEAST_3("ap-northeast-3", create("https://s3.ap-northeast-3.amazonaws.com")),
    AP_SOUTHEAST_1("ap-southeast-1", create("https://s3.ap-southeast-1.amazonaws.com")),
    AP_SOUTHEAST_2("ap-southeast-2", create("https://s3.ap-southeast-2.amazonaws.com")),

    EU_WEST_1("eu-west-1", create("https://s3.eu-west-1.amazonaws.com")),
    EU_WEST_2("eu-west-2", create("https://s3.eu-west-2.amazonaws.com")),
    EU_WEST_3("eu-west-3", create("https://s3.eu-west-3.amazonaws.com")),
    EU_CENTRAL_1("eu-central-1", create("https://s3.eu-central-1.amazonaws.com")),
    EU_NORTH_1("eu-north-1", create("https://s3.eu-north-1.amazonaws.com")),

    CA_CENTRAL_1("ca-central-1", create("https://s3.ca-central-1.amazonaws.com")),

    SA_EAST_1("sa-east-1", create("https://s3.sa-east-1.amazonaws.com")),

    CN_NORTH_1("cn-north-1", create("https://s3.cn-north-1.amazonaws.com.cn")),
    CN_NORTHWEST_1("cn-northwest-1", create("https://s3.cn-northwest-1.amazonaws.com.cn"));

    private final String regionName;
    private final URI endpoint;

    Region(final String regionName, final URI endpoint) {
        this.regionName = regionName;
        this.endpoint = endpoint;
    }

    public String getRegionName() {
        return regionName;
    }

    public URI getEndpoint() {
        return endpoint;
    }

    public static Region fromString(final String regionName) {
        return Stream.of(values())
            .filter(e -> e.regionName.equals(regionName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No matching constant for: " + regionName));
    }

    @Override
    public String toString() {
        return "Region{" +
            "regionName='" + regionName + '\'' +
            ", endpoint=" + endpoint +
            '}';
    }
}
