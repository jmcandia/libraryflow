package io.libraryflow.loans.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Clase de configuración para WebClient, que se utiliza para comunicarse con
 * los microservicios de libros y usuarios. Define dos beans de WebClient, uno
 * para cada servicio, con las URLs base configuradas a través de propiedades.
 */
@Configuration
public class WebClientConfig {
    @Value("${services.books.url}")
    private String booksServiceUrl;

    @Value("${services.users.url}")
    private String usersServiceUrl;

    @Bean
    public WebClient booksWebClient() {
        return WebClient.builder()
                .baseUrl(booksServiceUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Bean
    public WebClient usersWebClient() {
        return WebClient.builder()
                .baseUrl(usersServiceUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
