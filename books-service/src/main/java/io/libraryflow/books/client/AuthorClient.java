package io.libraryflow.books.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import io.libraryflow.books.dto.AuthorResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Componente que actúa como cliente para comunicarse con el microservicio de
 * autores. Utiliza WebClient para realizar solicitudes HTTP al servicio de
 * autores y obtener información sobre los autores. Este cliente se encarga de
 * abstraer la lógica de comunicación con el servicio de autores, proporcionando
 * métodos para obtener la información de un autor por su ID. La clase está
 * anotada con @Component para que Spring la detecte como un bean y @Slf4j para
 * habilitar el registro de eventos relacionados con las operaciones de
 * comunicación con el servicio de autores.
 */
@Component
@Slf4j
public class AuthorClient {

    @Autowired
    private WebClient authorsWebClient;

    /**
     * Obtiene la información de un autor por su ID utilizando el WebClient para
     * hacer una solicitud GET al servicio de autores.
     * 
     * @param authorId Long - El ID del autor para el cual se desea obtener la
     *                 información
     * @return AuthorResponse - La información del autor obtenida del servicio de
     *         autores
     */
    public AuthorResponse getAuthorById(Long authorId) {
        log.info("Fetching author with ID: {}", authorId);
        try {
            return authorsWebClient.get()
                    .uri("/authors/{authorId}", authorId)
                    .retrieve()
                    .bodyToMono(AuthorResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error fetching author with ID {}", authorId);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Author not found with ID " + authorId);
                default -> throw new RuntimeException("Error fetching author with ID " + authorId, ex);
            }
        }
    }
}
