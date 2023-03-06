package com.gs.bpe.quantum.dq.scheduler.config;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.configuration.JobRunrConfiguration;
import org.jobrunr.jobs.filters.RetryFilter;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private final int dashboardPort = 8090;
    private final int defaultRetries = 2;

//    @Autowired
//    private InMemoryStorageProvider inMemoryStorageProvider;

    @Autowired
    private MongoDBStorageProvider mongoDBStorageProvider;

    @Bean
    public JobRunrConfiguration.JobRunrConfigurationResult initJobRunr(ApplicationContext applicationContext) {
        return JobRunr.configure()
                .withJobFilter(new RetryFilter(defaultRetries))
                .useStorageProvider(mongoDBStorageProvider)
                .useJobActivator(applicationContext::getBean)
                .useBackgroundJobServer()
                .useDashboard(dashboardPort)
                .initialize();
    }


}
