package io.libraryflow.authors.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase AuthorRequest es un DTO (Data Transfer Object) que representa la
 * información de un autor que se enviará como solicitud en las solicitudes
 * HTTP. Contiene campos para el nombre completo, biografía, nacionalidad y
 * fecha de nacimiento del autor. La anotación @Data de Lombok genera
 * automáticamente los métodos getters, setters, equals, hashCode y toString.
 * Las anotaciones @NoArgsConstructor, @AllArgsConstructor y @Builder de Lombok
 * generan constructores argumentos, con todos los argumentos y un constructor
 * de tipo builder, respectivamente. Además, se utilizan anotaciones de
 * validación de Jakarta Bean Validation para asegurar que los campos cumplan
 * con ciertos requisitos, como no estar en blanco, tener un tamaño máximo y que
 * la fecha de nacimiento sea en el pasado. Este DTO se utiliza en la capa de
 * controlador para recibir los datos de entrada en las solicitudes HTTP y en la
 * capa de servicio para mapear estos datos a la entidad Author.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorRequest {
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    private String fullName;

    private String biography;

    @NotBlank(message = "Nationality is required")
    @Size(max = 100, message = "Nationality must be at most 100 characters")
    private String nationality;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
}
