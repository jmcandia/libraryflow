package io.libraryflow.loans.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import io.libraryflow.loans.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserClient {

    @Autowired
    private WebClient usersWebClient;

    public UserResponse getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        try {
            return usersWebClient.get()
                    .uri("/users/{id}", userId)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error fetching user with ID {}", userId);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("User not found with ID " + userId);
                default -> throw new RuntimeException("Error fetching user with ID " + userId);
            }
        }
    }
}
