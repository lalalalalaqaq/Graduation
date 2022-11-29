package com.orange.graduation.mq.kafka.Producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author orange.zhang
 * @date 2022/11/20 20:22
 */
@Slf4j
@Component
public class KafkaProducer {

    private final KafkaTemplate kafkaTemplate;

    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String key, String json , int partition) {
        ProducerRecord<String,String> record = new ProducerRecord<>(topic,partition,key,json);
        kafkaTemplate.send(record);
    }


}
