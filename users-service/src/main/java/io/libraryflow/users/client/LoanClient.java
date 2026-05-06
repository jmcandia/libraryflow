package io.libraryflow.users.client;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import io.libraryflow.users.dto.LoanResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoanClient {

    @Autowired
    private WebClient loansWebClient;

    /**
     * Obtiene los préstamos asociados a un usuario por su ID utilizando el
     * WebClient para hacer una solicitud GET al servicio de préstamos.
     * 
     * @param userId Long - El ID del usuario para el cual se desean obtener los
     *               préstamos
     * @return List<LoanResponse> - La lista de préstamos asociados al usuario
     *         obtenidos del servicio de préstamos
     */
    public List<LoanResponse> getLoansByUserId(Long userId) {
        log.info("Fetching loans for user with ID: {}", userId);
        try {
            return loansWebClient.get()
                    .uri("/loans/user/{userId}", userId)
                    .retrieve()
                    .bodyToFlux(LoanResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error fetching loans for user with ID {}", userId, ex);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("No loans found for user with ID " + userId);
                default -> throw new RuntimeException("Error fetching loans for user with ID " + userId, ex);
            }
        }
    }
}
