package com.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public String  getBaseUrl() {
        return "http://localhost:8080";
    }

}
