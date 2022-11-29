package com.orange.graduation.scheduler;

import com.orange.graduation.config.kfk.KafkaProps;
import com.orange.graduation.mq.kafka.Producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author orange.zhang
 * @date 2022/11/18 15:11
 */
@Slf4j
@EnableScheduling
@Component
public class AppScheduling {

    private final KafkaProducer kafkaProducer;

    public AppScheduling(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

//    @Scheduled(cron = "0/6 * * * * ? ")
    public void sendMsg(){
       kafkaProducer.send(KafkaProps.TEST_TOPIC,"key",String.valueOf(new Date()),0);
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void logInOneMin(){
        log.info("time : {} ", new Date());
    }

}
