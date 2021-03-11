package com.fun.order.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProducerConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    Producer producer(){
        return new Producer();
    }
}
