package io.github.linktosriram.s3lite.core.auth;

import io.github.linktosriram.s3lite.api.region.Region;

public interface RegionAwareSigner extends Signer {

    static RegionAwareSigner create() {
        return Aws4Signer.create();
    }

    Region getRegion();

    void setRegion(Region region);
}
