package io.libraryflow.authors.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase AuthorResponse es un DTO (Data Transfer Object) que representa la
 * información de un autor que se enviará como respuesta en las solicitudes
 * HTTP. Contiene campos para el ID del autor, nombre completo, biografía,
 * nacionalidad, fecha de nacimiento y la fecha de creación. La anotación @Data
 * de Lombok genera automáticamente los métodos getters, setters, equals,
 * hashCode y toString. Las anotaciones @NoArgsConstructor, @AllArgsConstructor
 * y @Builder de Lombok generan constructores sin argumentos, con todos los
 * argumentos y un constructor de tipo builder, respectivamente. Este DTO se
 * utiliza en la capa de controlador para enviar los datos de los autores en las
 * respuestas HTTP y en la capa de servicio para mapear los datos de la entidad
 * Author a este formato de respuesta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponse {
    private Long id;
    private String fullName;
    private String biography;
    private String nationality;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
}
