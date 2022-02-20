package ir.omidashouri.batch.steps;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("before Step Listener: " + stepExecution.getStepName());
        System.out.println("before Step Listener → execution context: " + stepExecution.getExecutionContext());
        System.out.println("before Step Listener → job execution context:" + stepExecution.getJobExecution().getExecutionContext());

        stepExecution.getExecutionContext().put("first_stepExecutionContext","first_stepExecutionContextValue");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("after Step Listener: " + stepExecution.getStepName());
        System.out.println("after Step Listener → execution context: " + stepExecution.getExecutionContext());
        System.out.println("after Step Listener → job execution context:" + stepExecution.getJobExecution().getExecutionContext());
        return ExitStatus.COMPLETED;
    }
}
