package org.jobrunr.scheduling;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.RecurringJob;
import org.jobrunr.jobs.lambdas.IocJobLambda;
import org.jobrunr.jobs.lambdas.IocJobLambdaFromStream;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.jobs.lambdas.JobLambdaFromStream;
import org.jobrunr.jobs.states.StateName;

public class BackgroundJob {
    private static JobScheduler jobScheduler;

    BackgroundJob() {
    }

    public static RecurringJob getRecurringJob(String id) {
        verifyJobScheduler();
        return jobScheduler.getRecurringJob(id);
    }


    public static String runAdhoc(RecurringJob recurringJob) {
        verifyJobScheduler();
        Job job = recurringJob.toEnqueuedJob();
        jobScheduler.saveJob(job);

        return job.getId().toString();
    }

    public static void cleanUp() {
        verifyJobScheduler();
        getStates().forEach(stateName -> {
            jobScheduler.deleteJobs(stateName, Instant.now());
        });
    }

    public static void cleanUp(StateName stateName) {
        jobScheduler.deleteJobs(stateName, Instant.now());
    }

    public static void cleanUp(StateName stateName, Instant updatedBefore) {
        jobScheduler.deleteJobs(stateName, updatedBefore);
    }

    private static List<StateName> getStates() {
        return Arrays.asList(StateName.ENQUEUED, StateName.FAILED, StateName.PROCESSING, StateName.SCHEDULED, StateName.SUCCEEDED);
    }
    public static JobId enqueue(JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.enqueue(job);
    }

    public static JobId enqueue(UUID id, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.enqueue(id, job);
    }

    public static <T> void enqueue(Stream<T> input, JobLambdaFromStream<T> jobFromStream) {
        verifyJobScheduler();
        jobScheduler.enqueue(input, jobFromStream);
    }

    public static <S> JobId enqueue(IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.enqueue(iocJob);
    }

    public static <S> JobId enqueue(UUID id, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.enqueue(id, iocJob);
    }

    public static <S, T> void enqueue(Stream<T> input, IocJobLambdaFromStream<S, T> iocJobFromStream) {
        verifyJobScheduler();
        jobScheduler.enqueue(input, iocJobFromStream);
    }

    public static JobId schedule(ZonedDateTime zonedDateTime, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(zonedDateTime, job);
    }

    public static JobId schedule(UUID id, ZonedDateTime zonedDateTime, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, zonedDateTime, job);
    }

    public static <S> JobId schedule(ZonedDateTime zonedDateTime, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(zonedDateTime, iocJob);
    }

    public static <S> JobId schedule(UUID id, ZonedDateTime zonedDateTime, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, zonedDateTime, iocJob);
    }

    public static JobId schedule(OffsetDateTime offsetDateTime, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(offsetDateTime, job);
    }

    public static JobId schedule(UUID id, OffsetDateTime offsetDateTime, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, offsetDateTime, job);
    }

    public static <S> JobId schedule(OffsetDateTime offsetDateTime, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(offsetDateTime, iocJob);
    }

    public static <S> JobId schedule(UUID id, OffsetDateTime offsetDateTime, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, offsetDateTime, iocJob);
    }

    public static JobId schedule(LocalDateTime localDateTime, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(localDateTime, job);
    }

    public static JobId schedule(UUID id, LocalDateTime localDateTime, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, localDateTime, job);
    }

    public static <S> JobId schedule(LocalDateTime localDateTime, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(localDateTime, iocJob);
    }

    public static <S> JobId schedule(UUID id, LocalDateTime localDateTime, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, localDateTime, iocJob);
    }

    public static JobId schedule(Instant instant, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(instant, job);
    }

    public static JobId schedule(UUID id, Instant instant, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, instant, job);
    }

    public static <S> JobId schedule(Instant instant, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(instant, iocJob);
    }

    public static <S> JobId schedule(UUID id, Instant instant, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.schedule(id, instant, iocJob);
    }

    public static void delete(UUID id) {
        verifyJobScheduler();
        jobScheduler.delete(id);
    }

    public static void delete(JobId jobId) {
        delete(jobId.asUUID());
    }

    public static String scheduleRecurrently(String cron, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.scheduleRecurrently(cron, job);
    }

    public static <S> String scheduleRecurrently(String cron, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.scheduleRecurrently(cron, iocJob);
    }

    public static String scheduleRecurrently(String id, String cron, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.scheduleRecurrently(id, cron, job);
    }

    public static <S> String scheduleRecurrently(String id, String cron, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.scheduleRecurrently(id, cron, iocJob);
    }

    public static String scheduleRecurrently(String id, String cron, ZoneId zoneId, JobLambda job) {
        verifyJobScheduler();
        return jobScheduler.scheduleRecurrently(id, cron, zoneId, job);
    }

    public static <S> String scheduleRecurrently(String id, String cron, ZoneId zoneId, IocJobLambda<S> iocJob) {
        verifyJobScheduler();
        return jobScheduler.scheduleRecurrently(id, cron, zoneId, iocJob);
    }

    public static void delete(String id) {
        verifyJobScheduler();
        jobScheduler.delete(id);
    }

    private static void verifyJobScheduler() {
        if (jobScheduler == null) {
            throw new IllegalStateException("The JobScheduler has not been initialized. Use the fluent JobRunr.configure() API to setup JobRunr or set the JobScheduler via the static setter method.");
        }
    }

    public static void setJobScheduler(JobScheduler jobScheduler) {
        BackgroundJob.jobScheduler = jobScheduler;
    }

}