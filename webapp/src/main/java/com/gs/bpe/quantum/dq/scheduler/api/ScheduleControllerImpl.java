package com.gs.bpe.quantum.dq.scheduler.api;

import com.gs.bpe.quantum.dq.scheduler.model.JobSchedule;
import com.gs.bpe.quantum.dq.scheduler.service.ScheduleService;
import org.jobrunr.jobs.states.StateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleControllerImpl implements ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Override
    public ResponseEntity<String> createOrUpdate(JobSchedule request) {
        String response = scheduleService.createOrUpdate(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        scheduleService.delete(id);
        return ResponseEntity.status(214).build();
    }

    @Override
    public ResponseEntity<JobSchedule> get(String id) {
        JobSchedule jobSchedule = scheduleService.fetchSchedule(id);
        return ResponseEntity.ok(jobSchedule);
    }

    @Override
    public ResponseEntity<String> run(String id) {
        String jobId = scheduleService.runOnDemand(id);
        return ResponseEntity.ok(jobId);
    }

    @Override
    public ResponseEntity<Void> clean(StateName stateName, Long cutOffTs) {
        scheduleService.clean(stateName, cutOffTs);
        return ResponseEntity.status(214).build();
    }

    @Override
    public ResponseEntity<Void> health() {
        return ResponseEntity.status(200).build();
    }
}
