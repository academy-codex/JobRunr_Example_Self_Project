package com.gs.bpe.quantum.dq.scheduler.service;

import com.gs.bpe.quantum.dq.scheduler.model.ScheduleMetaData;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobExecutorService {
    public void execute(ScheduleMetaData metaData) {
        String uuid = UUID.randomUUID().toString();
        System.out.println("UUID: " + uuid + " " + "Meta Data: " + metaData.toString());
        System.out.println("UUID: " + uuid + " " + "Sleeping for 1 min...");
        sleep(uuid);
        System.out.println("UUID: " + uuid + " " + "Out of sleep for Meta Data: " + metaData.toString());
        System.out.println("UUID: " + uuid + " " + "Sleeping for 1 min...");
        sleep(uuid);
        System.out.println("UUID: " + uuid + " " + "Execution Completed for Meta Data: " + metaData.toString());
    }

    private void sleep(String uuid) {
        try {
            Thread.sleep(10000 * 6);
        } catch (InterruptedException e) {
            System.out.println("UUID: " + uuid + " " + "Error Occurred during sleep...");
            throw new RuntimeException(e);
        }
    }
}
