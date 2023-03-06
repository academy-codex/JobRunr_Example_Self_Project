package com.gs.bpe.quantum.dq.scheduler.api;

import com.gs.bpe.quantum.dq.scheduler.model.JobSchedule;
import org.jobrunr.jobs.states.StateName;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler/schedule")
public interface ScheduleController {
    @GetMapping(produces = {MediaType.TEXT_HTML_VALUE})
    default public String index() {
        return "Hello World from JobController!<br />" +
                "You can:<br />" +
                "- <a href=\"/jobs/simple-job\">Enqueue a simple job</a><br />" +
                "- <a href=\"/jobs/simple-job-instance\">Enqueue a simple job using a service instance</a><br />" +
                "- <a href=\"/jobs/schedule-simple-job\">Schedule a simple job 3 hours from now using a service instance</a><br />" +
                "- <a href=\"/jobs/long-running-job\">Enqueue a long-running job</a><br />" +
                "- <a href=\"/jobs/long-running-job-with-job-context\">Enqueue a long-running job using a JobContext to log progress</a><br />" +
                "- Learn more on <a href=\"https://www.jobrunr.io/\">www.jobrunr.io</a><br />"
                ;
    }


    @PostMapping(value = "/createOrUpdate", produces = {MediaType.TEXT_PLAIN_VALUE})
    ResponseEntity<String> createOrUpdate(@RequestBody JobSchedule request);

    @DeleteMapping(value = "/delete", produces = {MediaType.TEXT_PLAIN_VALUE})
    ResponseEntity<Void> delete(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = "/get")
    ResponseEntity<JobSchedule> get(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = "/run", produces = {MediaType.TEXT_PLAIN_VALUE})
    ResponseEntity<String> run(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = "/clean", produces = {MediaType.TEXT_PLAIN_VALUE})
    ResponseEntity<Void> clean(@RequestParam(value = "state", required = false) StateName stateName,
                               @RequestParam(value = "cutOffTs", required = true) Long cutOffTs);

    @GetMapping(value = "/health")
    ResponseEntity<Void> health();

}
