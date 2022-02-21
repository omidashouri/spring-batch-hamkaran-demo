package ir.omidashouri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
//@EnableScheduling
public class SpringBatchHamkaranDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchHamkaranDemoApplication.class, args);
    }

}
