package com.volvo.thiagoteixeira.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.volvo.thiagoteixeira")
@EnableJpaRepositories("com.volvo.thiagoteixeira.dao")
@EntityScan("com.volvo.thiagoteixeira.model")
public class ThiagoTeixeiraApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThiagoTeixeiraApplication.class, args);
    }

}
