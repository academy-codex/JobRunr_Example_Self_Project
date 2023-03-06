package com.gs.bpe.quantum.dq.scheduler.service;

import com.gs.bpe.quantum.dq.scheduler.model.JobSchedule;
import com.gs.bpe.quantum.dq.scheduler.model.ScheduleMetaData;
import com.mongodb.client.MongoClient;
import org.jobrunr.jobs.RecurringJob;
import org.jobrunr.jobs.states.StateName;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.storage.JobNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
public class ScheduleService {

    @Autowired
    JobExecutorService jobExecutorService;

    @Autowired
    MongoClient mongoClient;

    public String createOrUpdate(JobSchedule request) {
        return BackgroundJob.scheduleRecurrently(request.getScheduleId(), request.getCronExpr(), ZoneId.of(ZoneId.SHORT_IDS.get("IST")),
                () -> jobExecutorService.execute(request.getMetaData()));
    }

    public void delete(String id) {
        BackgroundJob.delete(fetchRecurringJob(id).getId());
    }


    public JobSchedule fetchSchedule(String id) {
        long count = mongoClient.getDatabase("schedules").getCollection("test").countDocuments();
        System.out.println(count);
        return parseScheduleFromJob(fetchRecurringJob(id));
    }

    public String runOnDemand(String id) {
        return BackgroundJob.runAdhoc(fetchRecurringJob(id));
    }

    public void clean(StateName stateName, Long updatedBeforeEpochMilli) {
        if (updatedBeforeEpochMilli == 0 && stateName == null) {
            BackgroundJob.cleanUp();
        } else if (updatedBeforeEpochMilli == 0) {
            BackgroundJob.cleanUp(stateName);
        } else {
            Instant updatedBefore = Instant.ofEpochMilli(updatedBeforeEpochMilli);
            BackgroundJob.cleanUp(stateName, updatedBefore);
        }
    }

    private RecurringJob fetchRecurringJob(String id) {
        RecurringJob recurringJob = BackgroundJob.getRecurringJob(id);
        if (recurringJob == null) {
            throw new JobNotFoundException(id);
        }

        return recurringJob;
    }
    private JobSchedule parseScheduleFromJob(RecurringJob job) {
        String id = job.getId();
        String cronExpr = job.getCronExpression();
        ScheduleMetaData metaData = (ScheduleMetaData) job.getJobDetails().getJobParameters().get(0).getObject();

        return new JobSchedule(id, cronExpr, metaData);
    }

}
