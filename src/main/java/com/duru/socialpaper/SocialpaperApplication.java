package com.duru.socialpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialpaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialpaperApplication.class, args);
    }
}
