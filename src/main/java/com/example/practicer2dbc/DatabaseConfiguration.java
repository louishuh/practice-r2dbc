package com.example.practicer2dbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.test.StepVerifier;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DatabaseConfiguration {

    private final R2dbcEntityTemplate template;

    @PostConstruct
    public void init() {

        template.getDatabaseClient().sql("CREATE TABLE person" +
                        "(id VARCHAR(255) PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "age INT)")
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        template.insert(Person.class)
                .using(new Person("joe", "Joe", 34))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        template.select(Person.class)
                .first()
                .doOnNext(it -> log.info(it.toString()))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();


    }

}
