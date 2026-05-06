package io.libraryflow.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Clase de configuración para el cliente WebClient que se utiliza para
 * comunicarse con el microservicio de préstamos. Define un bean de WebClient
 * con la URL base configurada a través de propiedades.
 */
@Configuration
public class WebClientConfig {
    @Value("${services.loans.url}")
    private String loansServiceUrl;

    /**
     * Crea un bean de WebClient configurado para comunicarse con el microservicio
     * de préstamos. La URL base se establece a través de la propiedad
     * "services.loans.url". El WebClient se configura con encabezados
     * predeterminados para indicar que el contenido es JSON y que se aceptan
     * respuestas JSON. Esto facilita la integración con el microservicio de
     * préstamos, permitiendo realizar solicitudes HTTP de manera eficiente y
     * manejar las respuestas de forma adecuada.
     * 
     * @return WebClient - Un cliente configurado para comunicarse con el
     *         microservicio de préstamos.
     */
    @Bean
    public WebClient loansWebClient() {
        return WebClient.builder()
                .baseUrl(loansServiceUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
