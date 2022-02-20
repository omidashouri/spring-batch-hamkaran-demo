package ir.omidashouri.batch.jobs;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before Job: " + jobExecution.getJobInstance().getJobName());
        System.out.println("before job → Parameters: " + jobExecution.getJobParameters());
        System.out.println("before job → Execution Context: " + jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("first_jobExecutionContext","first_jobExecutionContextValue");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after Job: " + jobExecution.getJobInstance().getJobName());
        System.out.println("after job → Parameters: " + jobExecution.getJobParameters());
        System.out.println("after job → Execution Context: " + jobExecution.getExecutionContext());
    }
}
