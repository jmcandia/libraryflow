package io.libraryflow.loans.client;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import io.libraryflow.loans.dto.BookResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BookClient {

    @Autowired
    private WebClient booksWebClient;

    /**
     * Obtiene un libro por su ID utilizando el WebClient para hacer una solicitud
     * GET al servicio de libros. Si el libro no se encuentra, lanza una
     * NoSuchElementException. Si ocurre otro error, lanza una RuntimeException.
     * 
     * @param bookId Long - El ID del libro a obtener
     * @return BookResponse - El libro obtenido del servicio de libros
     */
    public BookResponse getBookById(Long bookId) {
        log.info("Fetching book with ID: {}", bookId);
        try {
            return booksWebClient.get()
                    .uri("/books/{id}", bookId)
                    .retrieve()
                    .bodyToMono(BookResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error fetching book with ID {}", bookId);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("Book not found with ID " + bookId);
                default -> throw new RuntimeException("Error fetching book with ID " + bookId);
            }
        }
    }

    /**
     * Marca un libro como prestado utilizando el WebClient para hacer una solicitud
     * PATCH al servicio de libros. Si el libro no se encuentra, lanza una
     * NoSuchElementException. Si ocurre otro error, lanza una RuntimeException.
     * 
     * @param bookId Long - El ID del libro a marcar como prestado
     * @return BookResponse - El libro actualizado del servicio de libros
     */
    public BookResponse markBookAsLoaned(Long bookId) {
        log.info("Marking book with ID: {} as loaned", bookId);
        try {
            return booksWebClient.patch()
                    .uri("/books/{id}/loan", bookId)
                    .retrieve()
                    .bodyToMono(BookResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error marking book with ID {} as loaned", bookId);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("Book not found with ID " + bookId);
                default -> throw new RuntimeException("Error marking book with ID " + bookId + " as loaned");
            }
        }
    }

    /**
     * Marca un libro como devuelto utilizando el WebClient para hacer una solicitud
     * PATCH al servicio de libros. Si el libro no se encuentra, lanza una
     * NoSuchElementException. Si ocurre otro error, lanza una RuntimeException.
     * 
     * @param bookId Long - El ID del libro a marcar como devuelto
     * @return BookResponse - El libro actualizado del servicio de libros
     */
    public BookResponse markBookAsReturned(Long bookId) {
        log.info("Marking book with ID: {} as returned", bookId);
        try {
            return booksWebClient.patch()
                    .uri("/books/{id}/return", bookId)
                    .retrieve()
                    .bodyToMono(BookResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error marking book with ID {} as returned", bookId);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("Book not found with ID " + bookId);
                default -> throw new RuntimeException("Error marking book with ID " + bookId + " as returned");
            }
        }
    }
}
