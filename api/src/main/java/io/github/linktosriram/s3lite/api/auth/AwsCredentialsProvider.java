package io.github.linktosriram.s3lite.api.auth;

import java.util.function.Supplier;

/**
 * Interface for providing AWS credentials. Implementations are free to use any strategy for providing AWS
 * credentials, such as simply providing static credentials that don't change, or more complicated implementations,
 * such as integrating with existing key management systems.
 */
@FunctionalInterface
public interface AwsCredentialsProvider extends Supplier<AwsCredentials> {
}
