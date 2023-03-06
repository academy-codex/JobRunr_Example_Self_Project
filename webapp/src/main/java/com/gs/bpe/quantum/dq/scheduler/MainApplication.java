package com.gs.bpe.quantum.dq.scheduler;

import com.gs.bpe.quantum.dq.scheduler.config.SchedulerConfig;
import com.gs.bpe.quantum.dq.scheduler.config.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SchedulerConfig.class, StorageConfig.class})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
