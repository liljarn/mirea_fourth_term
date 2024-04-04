package ru.mirea.task12.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArgsConfig {
    @Bean
    public List<String> args(ApplicationArguments applicationArguments) {
        return applicationArguments.getNonOptionArgs();
    }
}
