package com.mypracice.couchbase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Configuration
@Primary
public class N1QLReactiveCouchbaseConfiguration extends ReactiveCouchbaseConfiguration  {
    public N1QLReactiveCouchbaseConfiguration(CouchbaseProperties couchbaseProperties) {
        super(couchbaseProperties);
    }
}
