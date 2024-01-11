package com.example.springbatch_study.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class HelloJobConfiguration {

    @Bean
    public Job helloJob(JobRepository jobRepository, Step simpleStep1) {
        return new JobBuilder("HelloJob", jobRepository)
                .start(simpleStep1)
                .incrementer(new RunIdIncrementer())
                .build();
    }
    @Bean
    public Step helloJobStep(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("helloJobStep", jobRepository)
                .tasklet(testTasklet, platformTransactionManager).build();
    }
    @Bean
    public Tasklet helloTasklet(){
        return ((contribution, chunkContext) -> {
            log.info("Hello, Spring Batch!");
            return RepeatStatus.FINISHED;
        });
    }

}
