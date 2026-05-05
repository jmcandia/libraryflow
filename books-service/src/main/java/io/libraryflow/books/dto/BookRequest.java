package io.libraryflow.books.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase BookRequest es un DTO (Data Transfer Object) que representa la
 * información de un libro que se enviará como solicitud en las solicitudes
 * HTTP. Contiene campos para el título, resumen, ISBN, ID del autor y estado de
 * disponibilidad del libro. La anotación @Data de Lombok genera automáticamente
 * los métodos getters, setters, equals, hashCode y toString. Las
 * anotaciones @NoArgsConstructor, @AllArgsConstructor y @Builder de Lombok
 * generan constructores sin argumentos, con todos los argumentos y un
 * constructor de tipo builder, respectivamente. Además, se utilizan anotaciones
 * de validación de Jakarta Bean Validation para asegurar que los campos cumplan
 * con ciertos requisitos, como no estar en blanco, tener un tamaño máximo y que
 * el ID del autor y el estado de disponibilidad no sean nulos. Este DTO se
 * utiliza en la capa de controlador para recibir los datos de entrada en las
 * solicitudes HTTP y en la capa de servicio para mapear estos datos a la
 * entidad Book.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    private String summary;

    @NotBlank(message = "ISBN is required")
    @Size(max = 20, message = "ISBN must be at most 20 characters")
    private String isbn;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Available status is required")
    private Boolean available;
}
