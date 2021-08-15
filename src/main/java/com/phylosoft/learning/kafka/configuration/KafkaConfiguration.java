package com.phylosoft.learning.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Created by Andrew Kuzmin on 8/14/2021.
 */
@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic topicInput() {
        return TopicBuilder.name("topic-input").partitions(3).replicas(3).build();
    }

    @Bean
    public NewTopic topicOutput() {
        return TopicBuilder.name("topic-output").partitions(3).replicas(3).build();
    }


}
