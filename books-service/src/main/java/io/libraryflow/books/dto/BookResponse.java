package io.libraryflow.books.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase BookResponse es un DTO (Data Transfer Object) que representa la
 * información de un libro que se enviará como respuesta en las solicitudes
 * HTTP. Contiene campos para el ID del libro, título, resumen, ISBN, ID del
 * autor, estado de disponibilidad y la fecha de creación. La anotación @Data de
 * Lombok genera automáticamente los métodos getters, setters, equals, hashCode
 * y toString. Las anotaciones @NoArgsConstructor, @AllArgsConstructor
 * y @Builder de Lombok generan constructores sin argumentos, con todos los
 * argumentos y un constructor de tipo builder, respectivamente. Este DTO se
 * utiliza en la capa de controlador para enviar los datos de los libros en las
 * respuestas HTTP y en la capa de servicio para mapear los datos de la entidad
 * Book a este formato de respuesta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String summary;
    private String isbn;
    private AuthorResponse author;
    private Boolean available;
    private LocalDateTime createdAt;
}
