package com.BookService.BookService.Config;

import org.springframework.context.annotation.Bean;

public class RestTemplate
{
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

