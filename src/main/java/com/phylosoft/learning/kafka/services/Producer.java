package com.phylosoft.learning.kafka.services;

import com.github.javafaker.Faker;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * Created by Andrew Kuzmin on 8/14/2021.
 */
@Service
public class Producer {

    private final KafkaTemplate<Integer, String> template;

    Faker faker;

    public Producer(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void generate() {

        faker = Faker.instance();
        final Flux<Long> interval = Flux.interval(Duration.ofMillis(1_000));

        final Flux<String> quotes = Flux.fromStream(Stream.generate(() -> faker.hobbit().quote()));

        Flux.zip(interval, quotes)
                .map(it -> template.send("topic-input", faker.random().nextInt(42), it.getT2())).blockLast();
    }

}
