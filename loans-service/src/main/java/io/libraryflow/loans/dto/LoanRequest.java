package io.libraryflow.loans.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase LoanRequest es un DTO (Data Transfer Object) que representa la
 * información necesaria para crear un nuevo préstamo de libro. Contiene campos
 * para el ID del usuario y el ID del libro. La anotación @Data de Lombok genera
 * automáticamente los métodos getters, setters, equals, hashCode y toString.
 * Las anotaciones @NoArgsConstructor, @AllArgsConstructor y @Builder de Lombok
 * generan constructores sin argumentos, con todos los argumentos y un
 * constructor de tipo builder, respectivamente. Además, se utilizan anotaciones
 * de validación de Jakarta Bean Validation para asegurar que los campos no sean
 * nulos. Este DTO se utiliza en la capa de controlador para recibir los datos
 * de entrada en las solicitudes HTTP y en la capa de servicio para mapear estos
 * datos a la entidad Loan.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Book ID is required")
    private Long bookId;
}
