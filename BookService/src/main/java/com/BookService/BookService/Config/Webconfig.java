package com.BookService.BookService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Webconfig {
    /// using web flux
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build() ;
    }
}
