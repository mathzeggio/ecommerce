package com.site.ecommerce.config;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {
    final String ACCESS_KEY = "test";
    final String SECRET_KEY = "test";
    @Getter
    S3Client config;
    Region region = Region.US_EAST_1;

    public S3Config() {
        this.config = S3Client.builder()
            .endpointOverride(URI.create("https://s3.localhost.localstack.cloud:4566"))
            .credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
            .region(region)
            .build();
    }

}
