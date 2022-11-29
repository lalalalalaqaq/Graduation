package com.orange.graduation.mq.kafka.comsumer;

import com.orange.graduation.config.kfk.KafkaProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author orange.zhang
 * @date 2022/11/20 20:25
 */

@Slf4j
@Component
public class KafkaConsumer {

//    @KafkaListener(containerFactory = "TestConfigFactory",
//                   topicPartitions = {@TopicPartition(topic = KafkaProps.TEST_TOPIC,partitions = {"0"})})
    public void getMsg(@Payload(required = false) String msg) {
        log.info(msg);
    }

}
