package com.example.springbootproject.configuration;

import com.example.springbootproject.logger.DevLogger;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.logger.ProdLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringProjectConfiguration {

    @Value("${environment}")
    private String env;

    @Bean
    public Logger logger() {
        if ("dev".equals(env)) {
            return new DevLogger();
        } else {
            return new ProdLogger();
        }
    }
}
