package io.libraryflow.users.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase UserResponse es un DTO (Data Transfer Object) que representa la
 * información de un usuario que se enviará como respuesta en las solicitudes
 * HTTP. Contiene campos para el ID del usuario, nombre completo, correo
 * electrónico, número de teléfono y la fecha de creación. La anotación @Data de
 * Lombok genera automáticamente los métodos getters, setters, equals, hashCode
 * y toString. Las anotaciones @NoArgsConstructor, @AllArgsConstructor
 * y @Builder de Lombok generan constructores sin argumentos, con todos los
 * argumentos y un constructor de tipo builder, respectivamente. Este DTO se
 * utiliza en la capa de controlador para enviar los datos de los usuarios en
 * las respuestas HTTP y en la capa de servicio para mapear los datos de la
 * entidad User a este formato de respuesta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
