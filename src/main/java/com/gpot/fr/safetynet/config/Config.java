package com.gpot.fr.safetynet.config;

import com.gpot.fr.safetynet.repository.imp.DataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Config {
    @Bean
    CommandLineRunner initDatabase(){
        return args ->
            DataRepository.init();
    }
}
