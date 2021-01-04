package de.telran.domain_searcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Config {

    // Creates an object (bean) of DomainSearcher that can be used and automatically injected into other beans
    @Bean
    public DomainSearcher getDomainSearcher(){
        return new DomainSearcher(Arrays.asList("org", "de", "com", "ru", "ua", "net"));
    }
}