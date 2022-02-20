package ir.omidashouri.batch;

import ir.omidashouri.batch.itemprocessors.FirstItemProcessor;
import ir.omidashouri.batch.itemreaders.FirstItemReader;
import ir.omidashouri.batch.itemwriters.FirstItemWriter;
import ir.omidashouri.batch.jobs.FirstJobListener;
import ir.omidashouri.batch.steps.FirstStepListener;
import ir.omidashouri.batch.tasklets.SecondTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SampleJob {


    @Qualifier("customJobBuilderFactory")
    private final JobBuilderFactory customJobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FirstJobListener firstJobListener;
    private final FirstStepListener firstStepListener;
    private final SecondTasklet secondTasklet;


//    @Bean
    public Job firstJob() {
        return this.customJobBuilderFactory.get("First Job")
                .incrementer(new RunIdIncrementer())
                .start(this.firstStep())
                .next(this.secondStep())
                .listener(firstJobListener)
                .build();
    }

    private Step firstStep() {
        return this.stepBuilderFactory.get("First Step")
                .tasklet(this.firstTask())
                .listener(this.firstStepListener)
                .build();
    }

    private Step secondStep() {
        return this.stepBuilderFactory.get("Second Step")

//                1
//                .tasklet(this.secondTask())
                .tasklet(secondTasklet)

                .build();
    }

    private Tasklet firstTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)  {
                System.out.println("first tasklet step");
                System.out.println("first tasklet, chunk context → step context → stepExecutionContext: "+chunkContext.getStepContext().getStepExecutionContext());
                return RepeatStatus.FINISHED;
            }
        };
    }

//    1
    /*private Tasklet secondTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)  {
                System.out.println("second tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }*/


//    ------------------------------------------------------------



    private final FirstItemReader firstItemReader;
    private final FirstItemProcessor firstItemProcessor;
    private final FirstItemWriter firstItemWriter;


    @Bean
    public Job secondJob() {
        return this.customJobBuilderFactory.get("Second Job")
                .incrementer(new RunIdIncrementer())
                .start(this.firstChunkStep())
                .next(this.secondStep())
                .build();
    }

    private Step firstChunkStep(){
        return stepBuilderFactory.get("First Chunk Step")
                .<Integer, Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
