package com.phylosoft.learning.kafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by Andrew Kuzmin on 8/14/2021.
 */
@Service
public class Consumer {

    private final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = {"topic-output"}, groupId = "spring-boot-kafka")
    public void consume(ConsumerRecord<String, Long> record) {
        LOGGER.info("received = " + record.value() + " with key " + record.key());
    }


}
