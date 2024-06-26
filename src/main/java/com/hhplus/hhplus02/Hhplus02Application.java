package com.hhplus.hhplus02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hhplus02Application {

    public static void main(String[] args) {
        SpringApplication.run(Hhplus02Application.class, args);
    }

}
