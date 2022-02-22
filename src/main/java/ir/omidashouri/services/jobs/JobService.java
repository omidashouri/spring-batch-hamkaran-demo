package ir.omidashouri.services.jobs;

import ir.omidashouri.models.request.jobs.JobParamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobLauncher jobLauncher;
    @Qualifier("firstJob")
    private final Job firstJob;
    @Qualifier("secondJob")
    private final Job secondJob;
    @Qualifier("thirdJob")
    private final Job thirdJob;

    @Async
    public void startJob(String jobName) {
        Map<String, JobParameter> parameterMap = new LinkedHashMap<>();
        parameterMap.put("currentTime", new JobParameter(System.currentTimeMillis()));

        JobParameters jobParameters = new JobParameters(parameterMap);

        try {
            JobExecution jobExecution = null;
            if (jobName.equals("firstJob")) {
                jobExecution = jobLauncher.run(firstJob, jobParameters);
            } else if (jobName.equals("secondJob")) {
                jobExecution = jobLauncher.run(secondJob, jobParameters);
            }else if (jobName.equals("thirdJob")) {
                jobExecution = jobLauncher.run(thirdJob, jobParameters);
            }
            System.out.println("job Execution ID: " + jobExecution.getId());

        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    public void startJobByParam(String jobName, List<JobParamRequest> jobParamsRequest) {
        Map<String, JobParameter> parameterMap = new LinkedHashMap<>();
        parameterMap.put("currentTime", new JobParameter(System.currentTimeMillis()));

        jobParamsRequest.stream().forEach(jobParamRequest -> {
            parameterMap.put(jobParamRequest.getParamKey(),
                    new JobParameter(jobParamRequest.getParamValue()));
        });
        JobParameters jobParameters = new JobParameters(parameterMap);

        try {
            JobExecution jobExecution = null;
            if (jobName.equals("firstJob")) {
                jobExecution = jobLauncher.run(firstJob, jobParameters);
            } else if (jobName.equals("secondJob")) {
                jobExecution = jobLauncher.run(secondJob, jobParameters);
            } else if(jobName.equals("thirdJob")){
                jobExecution = jobLauncher.run(thirdJob, jobParameters);
            }
            System.out.println("job Execution ID: " + jobExecution.getId());

        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
