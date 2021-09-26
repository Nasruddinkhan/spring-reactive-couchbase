package com.mypracice.couchbase.config;

import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.mypracice.couchbase.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.RxJavaCouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.ReactiveRepositoryOperationsMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Slf4j
public abstract class ReactiveCouchbaseConfiguration extends AbstractReactiveCouchbaseConfiguration {
    private CouchbaseProperties couchbaseProperties;
    private CouchbaseEnvironment couchbaseEnvironment;

    protected ReactiveCouchbaseConfiguration(final CouchbaseProperties couchbaseProperties) {
        this.couchbaseProperties = couchbaseProperties;
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return List.of(couchbaseProperties.getBootstrapHosts());
    }

    @Override
    protected String getBucketName() {
        return couchbaseProperties.getBucketName();
    }

    @Override
    protected String getUsername() {
        return couchbaseProperties.getUsername();
    }

    @Override
    protected String getBucketPassword() {
        return couchbaseProperties.getPassword();
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        if (couchbaseEnvironment == null) {
            couchbaseEnvironment = DefaultCouchbaseEnvironment.create();
            // couchbaseEnvironment = DefaultCouchbaseEnvironment.builder().queryServiceConfig(QueryServiceConfig.create(0, 12, 10)).build();

        }

        log.info("ReactiveCouchbaseConfiguration.getEnvironment" + couchbaseEnvironment.connectTimeout());
        log.info("ReactiveCouchbaseConfiguration.getEnvironment" + couchbaseEnvironment.queryTimeout());
        return couchbaseEnvironment;
    }

    @Override
    @Bean(name = BeanNames.COUCHBASE_CLUSTER_INFO)
    public ClusterInfo couchbaseClusterInfo() throws Exception {
        return couchbaseCluster().authenticate(couchbaseProperties.getUsername(), couchbaseProperties.getPassword()).clusterManager().info();
    }

    @Override
    @Bean(name = BeanNames.RXJAVA1_COUCHBASE_TEMPLATE)
    public RxJavaCouchbaseTemplate reactiveCouchbaseTemplate() throws Exception {
        var template = new RxJavaCouchbaseTemplate(couchbaseConfigurer().couchbaseClusterInfo(),
                couchbaseConfigurer().couchbaseClient(), mappingCouchbaseConverter(), translationService());
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Override
    @Bean(name = BeanNames.REACTIVE_COUCHBASE_OPERATIONS_MAPPING)
    public ReactiveRepositoryOperationsMapping reactiveRepositoryOperationsMapping(
            RxJavaCouchbaseTemplate couchbaseTemplate) throws Exception {
        var baseMapping = new ReactiveRepositoryOperationsMapping(couchbaseTemplate);
        configureReactiveRepositoryOperationsMapping(baseMapping);
        return baseMapping;
    }

    @Bean
    public Sinks.Many<EmployeeDto> sink() {
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<EmployeeDto> productBroadcast(Sinks.Many<EmployeeDto> sink) {
        return sink.asFlux();
    }

}
