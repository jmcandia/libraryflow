package io.libraryflow.loans.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase LoanResponse es un DTO (Data Transfer Object) que representa la
 * información de un préstamo de libro que se enviará como respuesta en las
 * solicitudes HTTP. Contiene campos para el ID del préstamo, ID del usuario, ID
 * del libro, fecha del préstamo y fecha de devolución. La anotación @Data de
 * Lombok genera automáticamente los métodos getters, setters, equals, hashCode
 * y toString. Las anotaciones @NoArgsConstructor, @AllArgsConstructor
 * y @Builder de Lombok generan constructores sin argumentos, con todos los
 * argumentos y un constructor de tipo builder, respectivamente. La
 * anotación @JsonInclude(JsonInclude.Include.NON_NULL) de Jackson se utiliza
 * para indicar que los campos con valores nulos no deben incluirse en la
 * representación JSON de la respuesta. Este DTO se utiliza en la capa de
 * controlador para enviar los datos de los préstamos en las respuestas HTTP y
 * en la capa de servicio para mapear los datos de la entidad Loan a este
 * formato de respuesta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {
    private Long id;
    private UserResponse user;
    private BookResponse book;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
}
