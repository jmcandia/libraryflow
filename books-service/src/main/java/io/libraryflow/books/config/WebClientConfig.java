package io.libraryflow.books.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${services.authors.url}")
    private String authorsServiceUrl;

    @Bean
    public WebClient authorsWebClient() {
        return WebClient.builder()
                .baseUrl(authorsServiceUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
