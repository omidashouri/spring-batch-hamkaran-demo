package ir.omidashouri.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


     @Bean
    @ConfigurationProperties("spring.batch")
    public DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

   @Bean
    public DataSource dataSource(DataSourceProperties batchDataSourceProperties) {
         return batchDataSourceProperties.initializeDataSourceBuilder()
        .build();
    }

/*    @Bean
    @ConfigurationProperties(prefix = "spring.batch.datasource")
    public DataSource dataSourceBatch(){
        return DataSourceBuilder.create().build();
    }*/

    private PlatformTransactionManager transactionManager(){
        return new ResourcelessTransactionManager();
    }
    private JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(this.dataSource(batchDataSourceProperties()));
        factory.setTransactionManager(transactionManager());
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factory.setTablePrefix("SPRINGBATCH.BATCH_");
        factory.setDatabaseType("ORACLE");
        factory.setMaxVarCharLength(1000);
        return factory.getObject();
    }

    @Bean
    public JobLauncher jobLauncherBatch() throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.setJobRepository(this.jobRepository());
        return jobLauncher;
    }

    @Bean
    public JobBuilderFactory customJobBuilderFactory() throws Exception {
        return new JobBuilderFactory(this.jobRepository());
    }
}
