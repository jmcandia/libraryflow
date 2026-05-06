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

    /**
     * Crea un bean de WebClient configurado para comunicarse con el microservicio
     * de libros. La URL base se establece a través de la propiedad
     * "services.books.url". El WebClient se configura con encabezados
     * predeterminados para indicar que el contenido es JSON y que se aceptan
     * respuestas JSON. Esto facilita la integración con el microservicio de libros,
     * permitiendo realizar solicitudes HTTP de manera eficiente y manejar las
     * respuestas de forma adecuada.
     * 
     * @return WebClient - Un cliente configurado para comunicarse con el
     *         microservicio de libros.
     */
    @Bean
    public WebClient booksWebClient() {
        return WebClient.builder()
                .baseUrl(booksServiceUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    /**
     * Crea un bean de WebClient configurado para comunicarse con el microservicio
     * de usuarios. La URL base se establece a través de la propiedad
     * "services.users.url". El WebClient se configura con encabezados
     * predeterminados para indicar que el contenido es JSON y que se aceptan
     * respuestas JSON. Esto facilita la integración con el microservicio de
     * usuarios, permitiendo realizar solicitudes HTTP de manera eficiente y manejar
     * las respuestas de forma adecuada
     * 
     * @return WebClient - Un cliente configurado para comunicarse con el
     *         microservicio de usuarios.
     */
    @Bean
    public WebClient usersWebClient() {
        return WebClient.builder()
                .baseUrl(usersServiceUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
