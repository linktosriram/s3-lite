# s3-lite

A Barebones Java client for AWS S3

## Motivation

Below is the dependency tree of AWS S3 Java SDK v2

```
$ gradle dependencies --configuration runtimeClasspath

\--- software.amazon.awssdk:s3:2.17.261
     +--- software.amazon.awssdk:aws-xml-protocol:2.17.261
     |    +--- software.amazon.awssdk:aws-query-protocol:2.17.261
     |    |    +--- software.amazon.awssdk:protocol-core:2.17.261
     |    |    |    +--- software.amazon.awssdk:sdk-core:2.17.261
     |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    +--- software.amazon.awssdk:http-client-spi:2.17.261
     |    |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    |    +--- software.amazon.awssdk:utils:2.17.261
     |    |    |    |    |    |    +--- org.reactivestreams:reactive-streams:1.0.3
     |    |    |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    |    |    \--- org.slf4j:slf4j-api:1.7.30
     |    |    |    |    |    +--- software.amazon.awssdk:metrics-spi:2.17.261
     |    |    |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    |    |    \--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    |    |    \--- org.reactivestreams:reactive-streams:1.0.3
     |    |    |    |    +--- software.amazon.awssdk:metrics-spi:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:profiles:2.17.261
     |    |    |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    |    |    \--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    +--- org.slf4j:slf4j-api:1.7.30
     |    |    |    |    \--- org.reactivestreams:reactive-streams:1.0.3
     |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    \--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     |    |    +--- software.amazon.awssdk:aws-core:2.17.261
     |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    +--- software.amazon.awssdk:regions:2.17.261
     |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:sdk-core:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:profiles:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:json-utils:2.17.261
     |    |    |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    |    \--- software.amazon.awssdk:third-party-jackson-core:2.17.261
     |    |    |    |    \--- org.slf4j:slf4j-api:1.7.30
     |    |    |    +--- software.amazon.awssdk:auth:2.17.261
     |    |    |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:sdk-core:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:regions:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:profiles:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     |    |    |    |    +--- software.amazon.awssdk:json-utils:2.17.261 (*)
     |    |    |    |    \--- software.amazon.eventstream:eventstream:1.0.1
     |    |    |    +--- software.amazon.awssdk:profiles:2.17.261 (*)
     |    |    |    +--- software.amazon.awssdk:sdk-core:2.17.261 (*)
     |    |    |    +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     |    |    |    +--- software.amazon.awssdk:metrics-spi:2.17.261 (*)
     |    |    |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    |    |    \--- software.amazon.eventstream:eventstream:1.0.1
     |    |    +--- software.amazon.awssdk:sdk-core:2.17.261 (*)
     |    |    +--- software.amazon.awssdk:annotations:2.17.261
     |    |    +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     |    |    \--- software.amazon.awssdk:utils:2.17.261 (*)
     |    +--- software.amazon.awssdk:protocol-core:2.17.261 (*)
     |    +--- software.amazon.awssdk:aws-core:2.17.261 (*)
     |    +--- software.amazon.awssdk:sdk-core:2.17.261 (*)
     |    +--- software.amazon.awssdk:annotations:2.17.261
     |    +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     |    \--- software.amazon.awssdk:utils:2.17.261 (*)
     +--- software.amazon.awssdk:protocol-core:2.17.261 (*)
     +--- software.amazon.awssdk:arns:2.17.261
     |    +--- software.amazon.awssdk:annotations:2.17.261
     |    \--- software.amazon.awssdk:utils:2.17.261 (*)
     +--- software.amazon.awssdk:profiles:2.17.261 (*)
     +--- software.amazon.awssdk:sdk-core:2.17.261 (*)
     +--- software.amazon.awssdk:auth:2.17.261 (*)
     +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     +--- software.amazon.awssdk:regions:2.17.261 (*)
     +--- software.amazon.awssdk:annotations:2.17.261
     +--- software.amazon.awssdk:utils:2.17.261 (*)
     +--- software.amazon.awssdk:aws-core:2.17.261 (*)
     +--- software.amazon.awssdk:metrics-spi:2.17.261 (*)
     +--- software.amazon.awssdk:apache-client:2.17.261
     |    +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
     |    +--- software.amazon.awssdk:metrics-spi:2.17.261 (*)
     |    +--- software.amazon.awssdk:utils:2.17.261 (*)
     |    +--- software.amazon.awssdk:annotations:2.17.261
     |    +--- org.apache.httpcomponents:httpclient:4.5.13
     |    |    +--- org.apache.httpcomponents:httpcore:4.4.13
     |    |    +--- commons-logging:commons-logging:1.2
     |    |    \--- commons-codec:commons-codec:1.11
     |    \--- org.apache.httpcomponents:httpcore:4.4.13
     \--- software.amazon.awssdk:netty-nio-client:2.17.261
          +--- software.amazon.awssdk:annotations:2.17.261
          +--- software.amazon.awssdk:http-client-spi:2.17.261 (*)
          +--- software.amazon.awssdk:utils:2.17.261 (*)
          +--- software.amazon.awssdk:metrics-spi:2.17.261 (*)
          +--- io.netty:netty-codec-http:4.1.77.Final
          |    +--- io.netty:netty-common:4.1.77.Final
          |    +--- io.netty:netty-buffer:4.1.77.Final
          |    |    \--- io.netty:netty-common:4.1.77.Final
          |    +--- io.netty:netty-transport:4.1.77.Final
          |    |    +--- io.netty:netty-common:4.1.77.Final
          |    |    +--- io.netty:netty-buffer:4.1.77.Final (*)
          |    |    \--- io.netty:netty-resolver:4.1.77.Final
          |    |         \--- io.netty:netty-common:4.1.77.Final
          |    +--- io.netty:netty-codec:4.1.77.Final
          |    |    +--- io.netty:netty-common:4.1.77.Final
          |    |    +--- io.netty:netty-buffer:4.1.77.Final (*)
          |    |    \--- io.netty:netty-transport:4.1.77.Final (*)
          |    \--- io.netty:netty-handler:4.1.77.Final
          |         +--- io.netty:netty-common:4.1.77.Final
          |         +--- io.netty:netty-resolver:4.1.77.Final (*)
          |         +--- io.netty:netty-buffer:4.1.77.Final (*)
          |         +--- io.netty:netty-transport:4.1.77.Final (*)
          |         \--- io.netty:netty-codec:4.1.77.Final (*)
          +--- io.netty:netty-codec-http2:4.1.77.Final
          |    +--- io.netty:netty-common:4.1.77.Final
          |    +--- io.netty:netty-buffer:4.1.77.Final (*)
          |    +--- io.netty:netty-transport:4.1.77.Final (*)
          |    +--- io.netty:netty-codec:4.1.77.Final (*)
          |    +--- io.netty:netty-handler:4.1.77.Final (*)
          |    \--- io.netty:netty-codec-http:4.1.77.Final (*)
          +--- io.netty:netty-codec:4.1.77.Final (*)
          +--- io.netty:netty-transport:4.1.77.Final (*)
          +--- io.netty:netty-common:4.1.77.Final
          +--- io.netty:netty-buffer:4.1.77.Final (*)
          +--- io.netty:netty-handler:4.1.77.Final (*)
          +--- io.netty:netty-transport-classes-epoll:4.1.77.Final
          |    +--- io.netty:netty-common:4.1.77.Final
          |    +--- io.netty:netty-buffer:4.1.77.Final (*)
          |    +--- io.netty:netty-transport:4.1.77.Final (*)
          |    \--- io.netty:netty-transport-native-unix-common:4.1.77.Final
          |         +--- io.netty:netty-common:4.1.77.Final
          |         +--- io.netty:netty-buffer:4.1.77.Final (*)
          |         \--- io.netty:netty-transport:4.1.77.Final (*)
          +--- org.reactivestreams:reactive-streams:1.0.3
          \--- org.slf4j:slf4j-api:1.7.30
```

If that might be too much for your use-case (The uber-jar size is 10.59 MB)

## Features

* Java 8+
* Zero dependencies
* Extremely lightweight core (< 100 KB)
* Pluggable HTTP client
* JSR 310 compatible
* No code generation
* Cursor based StAX XML Parser

```
Benchmark       Mode  Cnt    Score   Error  Units
XMLParse.JAXB  thrpt   25   58.890 ± 1.534  ops/s
XMLParse.StAX  thrpt   25  336.818 ± 1.159  ops/s
```

### Pluggable HTTP client

s3-lite is not tightly-coupled with any HTTP implementation. It provides a pluggable HTTP layer.

Default implementations are provided for Apache HTTP Client and JVM's URLConnection, but you can replace it with another implementation that better suits your use-case.
Although the Apache HTTP Client works well in general, there are often benefits to using a client that is more optimized for your runtime environment.

For example, in AWS Lambda, where startup time is one of the biggest latency concerns,
you might want to use an HTTP client based on the JVM’s lightweight URLConnection, instead of Apache’s higher-throughput, but slower-to-start HTTP client.

## Creation

```java
S3Client client = new DefaultS3ClientBuilder()
    .credentialsProvider(() -> AwsBasicCredentials.create("access-key", "secret-access-key"))
    .region(Region.EU_CENTRAL_1)
    .httpClient(ApacheSdkHttpClient.defaultClient()) // Or use URLConnectionSdkHttpClient
    .build();
```

## Client Lifecycle

Service clients in the SDK are thread-safe. For best performance, treat them as long-lived objects. Each client has its own connection pool resource that is released when the client is garbage collected.

For best practices, explicitly close a client by calling the `close()` method.

```java
S3Client client = ...
client.close();
```

## Usage

### List Objects

```java
ListObjectsV2Request request = ListObjectsV2Request.builder();
    .bucketName("bucket-name")
    .maxKeys(10)
    .build();

ListObjectsV2Response response = client.listObjectsV2(request);
System.out.println("Response: " + response);
```

### Get Object

```java
GetObjectRequest request = GetObjectRequest.builder()
    .bucketName("bucket-name")
    .key("key")
    .build();

// Save the response in path
GetObjectResponse response = client.getObject(request, path);

// Save the response in file
GetObjectResponse response = client.getObject(request, ResponseTransformer.toFile(file));

// Get raw bytes
ResponseBytes<GetObjectResponse> responseBytes = client.getObjectAsBytes(request);

// Get the InputStream directly
ResponseInputStream<GetObjectResponse> responseIs = client.getObject(request);
```

### Delete Object

```java
DeleteObjectRequest request = DeleteObjectRequest.builder()
    .bucketName("bucket-name")
    .key("key")
    .build();

DeleteObjectResponse response = client.deleteObject(request);
```

### Put Object

```java
PutObjectRequest request = PutObjectRequest.builder()
    .bucketName("bucket-name")
    .key("key")
    .build();

// Upload the path
PutObjectResponse response = client.putObject(request, path);

// Upload the file
PutObjectResponse response = client.putObject(request, RequestBody.fromFile(file));

// Upload raw bytes
PutObjectResponse response = client.putObject(request, RequestBody.fromBytes(bytes));

// Upload the content from InputStream
PutObjectResponse response = client.putObject(request, RequestBody.fromInputStream(() -> is, contentLength));
```

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.
