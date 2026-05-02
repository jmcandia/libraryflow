package io.libraryflow.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase UserRequest es un DTO (Data Transfer Object) que representa la
 * información necesaria para crear o actualizar un usuario. Contiene campos
 * para el nombre completo, correo electrónico y número de teléfono del usuario.
 * La anotación @Data de Lombok genera automáticamente los métodos getters,
 * setters, equals, hashCode y toString. Las
 * anotaciones @NoArgsConstructor, @AllArgsConstructor y @Builder de Lombok
 * generan constructores sin argumentos, con todos los argumentos y un
 * constructor de tipo builder, respectivamente. Además, se utilizan anotaciones
 * de validación de Jakarta Bean Validation para asegurar que los campos cumplan
 * con ciertos requisitos, como no estar en blanco, tener un tamaño máximo y ser
 * un correo electrónico válido. Este DTO se utiliza en la capa de controlador
 * para recibir los datos de entrada en las solicitudes HTTP y en la capa de
 * servicio para mapear estos datos a la entidad User.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must be at most 100 characters")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 20, message = "Phone number must be at most 20 characters")
    private String phone;
}
