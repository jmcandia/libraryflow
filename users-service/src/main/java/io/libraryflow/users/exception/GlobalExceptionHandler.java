package io.libraryflow.users.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.libraryflow.users.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * GlobalExceptionHandler es una clase que maneja las excepciones de manera
 * global en la aplicación. Utiliza la anotación @RestControllerAdvice para
 * indicar que es un controlador de excepciones y la anotación @Slf4j para
 * habilitar el registro de logs. Esta clase define varios métodos que manejan
 * diferentes tipos de excepciones, como DataIntegrityViolationException,
 * NoSuchElementException, MethodArgumentNotValidException y RuntimeException.
 * Cada método crea una respuesta de error estructurada utilizando la clase
 * ApiErrorResponse, que incluye información sobre el error, como el timestamp,
 * el status, el error, el mensaje y el path de la solicitud. Luego, devuelve
 * una respuesta HTTP con el estado correspondiente y el cuerpo de la respuesta
 * de error. Además, cada método registra el error en los logs para facilitar la
 * depuración. Esta clase ayuda a centralizar el manejo de excepciones en la
 * aplicación y a proporcionar respuestas de error consistentes y estructuradas
 * a los clientes.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de tipo DataIntegrityViolationException que se lanzan
     * cuando se viola una restricción de integridad de datos en la base de datos.
     * Crea una respuesta de error estructurada utilizando la clase
     * ApiErrorResponse, que incluye información sobre el error, como el timestamp,
     * el status, el error, el mensaje y el path de la solicitud. Devuelve una
     * respuesta HTTP con el estado 409 (CONFLICT) y el cuerpo de la respuesta de
     * error. Además, registra el error en los logs para facilitar la depuración.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {
        log.error("Data integrity violation: {}", ex.getMessage());
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.name())
                .message("Data integrity violation")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo NoSuchElementException que se lanzan cuando no
     * se encuentra un elemento en la base de datos. Crea una respuesta de error
     * estructurada utilizando la clase ApiErrorResponse, que incluye información
     * sobre el error, como el timestamp, el status, el error, el mensaje y el path
     * de la solicitud. Devuelve una respuesta HTTP con el estado 404 (NOT_FOUND) y
     * el cuerpo de la respuesta de error. Además, registra el error en los logs
     * para facilitar la depuración.
     * 
     * @param ex      NoSuchElementException - La excepción que se ha lanzado
     * @param request HttpServletRequest - El objeto de solicitud HTTP que contiene
     *                información sobre la solicitud que causó la excepción
     * @return ResponseEntity<ApiErrorResponse> - La respuesta HTTP que contiene el
     *         estado 404 y el cuerpo de la respuesta de error estructurada
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(NoSuchElementException ex,
            HttpServletRequest request) {
        log.error("Entity not found: {}", ex.getMessage());
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo MethodArgumentNotValidException que se lanzan
     * cuando la validación de los argumentos de un método falla. Crea una respuesta
     * de error estructurada utilizando la clase ApiErrorResponse, que incluye
     * información sobre el error, como el timestamp, el status, el error, el
     * mensaje, el path de la solicitud y una lista de errores de validación.
     * Devuelve una respuesta HTTP con el estado 400 (BAD_REQUEST) y el cuerpo de la
     * respuesta de error. Además, registra el error en los logs para facilitar la
     * depuración.
     * 
     * @param ex      MethodArgumentNotValidException - La excepción que se ha
     *                lanzado cuando la validación de los argumentos de un método
     *                falla
     * @param request HttpServletRequest - El objeto de solicitud HTTP que contiene
     *                información sobre la solicitud que causó la excepción
     * @return ResponseEntity<ApiErrorResponse> - La respuesta HTTP que contiene el
     *         estado 400 y el cuerpo de la respuesta de error estructurada con
     *         detalles de los errores de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        log.error("Invalid request: {}", ex.getMessage());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message("Invalid request")
                .path(request.getRequestURI())
                .errors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo RuntimeException que se lanzan cuando ocurre
     * un error inesperado en la aplicación. Crea una respuesta de error
     * estructurada utilizando la clase ApiErrorResponse, que incluye información
     * sobre el error, como el timestamp, el status, el error, el mensaje y el path
     * de la solicitud. Devuelve una respuesta HTTP con el estado 500
     * (INTERNAL_SERVER_ERROR) y el cuerpo de la respuesta de error. Además,
     * registra el error en los logs para facilitar la depuración.
     * 
     * @param ex      RuntimeException - La excepción que se ha lanzado
     * @param request HttpServletRequest - El objeto de solicitud HTTP que contiene
     *                información sobre la solicitud que causó la excepción
     * @return ResponseEntity<ApiErrorResponse> - La respuesta HTTP que contiene el
     *         estado 500 y el cuerpo de la respuesta de error estructurada
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex,
            HttpServletRequest request) {
        log.error("Unexpected error: {}", ex.getMessage());
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message("Unexpected error")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
