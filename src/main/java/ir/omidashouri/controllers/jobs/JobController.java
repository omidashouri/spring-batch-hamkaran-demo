package ir.omidashouri.controllers.jobs;

import ir.omidashouri.models.request.jobs.JobParamRequest;
import ir.omidashouri.services.jobs.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final JobOperator jobOperator;

    //    localhost:8080/api/job/start/firstJob
    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) {

        jobService.startJob(jobName);

        return "Job Started ...";
    }

    //    localhost:8080/api/job/start/firstJob
    /*
        [
            {
                "paramKey":"paramKey_1",
                "paramValue":"paramValue_1"
            },
            {
                "paramKey":"paramKey_2",
                "paramValue":"paramValue_2"
            }
         ]
     */
    @PostMapping("/start/{jobName}")
    public String startJobByParam(@PathVariable String jobName, @RequestBody List<JobParamRequest> jobParamRequest) {

        jobService.startJobByParam(jobName, jobParamRequest);

        return "Job Started ...";
    }


    //    localhost:8080/api/job/stop/119
    @GetMapping("/stop/{executionId}")
    public String stopJob(@PathVariable Long executionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
        jobOperator.stop(executionId);
        return "Job Stopped ...";
    }
}
