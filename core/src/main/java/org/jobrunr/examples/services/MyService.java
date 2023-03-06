package org.jobrunr.examples.services;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.spring.annotations.Recurring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * This is a simple spring service
 */
@Component
public class MyService implements MyServiceInterface {

    private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(MyService.class));

    @Recurring(id = "my-recurring-job", cron = "0 0/15 * * *")
    @Job(name = "My recurring job")
    public void doRecurringJob() {
        System.out.println("Doing some work without arguments");
    }

    public void handleJob(JobEntity entity) {
        if (entity.getAlias().equalsIgnoreCase("fail_job")) {
            throw new RuntimeException("Failing job to test resiliency of retry");
        }

        UUID uuid = UUID.randomUUID();
        System.out.println("UUID: " + uuid.toString());
        BackgroundJob.enqueue(uuid, () -> System.out.println("Doing some work: " + entity.getAlias()));
    }

    public void doLongRunningJob(String anArgument) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void doLongRunningJobWithJobContext(String anArgument, JobContext jobContext) {
        try {
            LOGGER.warn("Starting long running job...");
            final JobDashboardProgressBar progressBar = jobContext.progressBar(10);
            for (int i = 0; i < 10; i++) {
                LOGGER.info(String.format("Processing item %d: %s", i, anArgument));
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(15000);
                progressBar.increaseByOne();
            }
            LOGGER.warn("Finished long running job...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
