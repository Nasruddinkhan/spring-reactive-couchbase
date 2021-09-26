package com.mypracice.couchbase.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.couchbase")
public class CouchbaseProperties {
    private Integer port;
    private String password;
    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;
    @Value("${spring.couchbase.bootstrap-hosts}")
    private String bootstrapHosts;
    private String username;
    private List<String> rule;
}
