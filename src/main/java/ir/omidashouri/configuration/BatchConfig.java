package ir.omidashouri.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfigurer {


    private final PlatformTransactionManager transactionManager;
    private final DataSource dataSource;

    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(this.dataSource);
        factory.setTransactionManager(this.transactionManager);
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factory.setTablePrefix("SPRINGBATCH.BATCH_");
        factory.setMaxVarCharLength(1000);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Override
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(this.createJobRepository());
//        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Override
    protected JobExplorer createJobExplorer() throws Exception {
        return this.jobExplorerFactoryBean().getObject();
    }

    @Bean
    public JobExplorerFactoryBean jobExplorerFactoryBean() throws Exception {
        JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
        jobExplorerFactoryBean.setDataSource(this.dataSource);
        jobExplorerFactoryBean.setTablePrefix("SPRINGBATCH.BATCH_");
        jobExplorerFactoryBean.afterPropertiesSet();
        return jobExplorerFactoryBean;
    }

    @Bean
    public JobBuilderFactory customJobBuilderFactory() throws Exception {
        return new JobBuilderFactory(this.createJobRepository());
    }

/*    @Bean
    public JobOperator jobOperator() throws Exception {
        SimpleJobOperator jobOperator = new SimpleJobOperator();

        this.createJobExplorer().getJobNames();
        jobOperator.setJobExplorer(this.createJobExplorer());
        jobOperator.setJobLauncher(this.createJobLauncher());
        jobOperator.setJobRegistry(new MapJobRegistry());
        jobOperator.setJobRepository(this.createJobRepository());
        return jobOperator;
    }*/
}
