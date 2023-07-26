package com.hsbc.insh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InshApplication {

    public static void main(String[] args) {
        SpringApplication.run(InshApplication.class, args);
    }

}
