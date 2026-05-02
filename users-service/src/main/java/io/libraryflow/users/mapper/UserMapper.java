package io.libraryflow.users.mapper;

import org.springframework.stereotype.Component;

import io.libraryflow.users.dto.UserRequest;
import io.libraryflow.users.dto.UserResponse;
import io.libraryflow.users.model.User;

/**
 * La clase UserMapper es un componente de Spring que se encarga de mapear entre
 * las entidades de usuario (User) y los objetos de transferencia de datos (DTO)
 * UserRequest y UserResponse. Proporciona métodos para convertir un UserRequest
 * a una entidad User y para convertir una entidad User a un UserResponse. Este
 * mapeo es útil para separar la lógica de negocio de la representación de los
 * datos en las solicitudes y respuestas HTTP, permitiendo una mayor
 * flexibilidad y mantenibilidad en el código.
 */
@Component
public class UserMapper {

    /**
     * Convierte un UserRequest a una entidad User. Toma los campos del UserRequest
     * y los asigna a un nuevo objeto User utilizando el patrón de diseño Builder.
     * El ID y la fecha de creación no se establecen en este método, ya que se
     * generarán automáticamente al persistir la entidad en la base de datos. Este
     * método se utiliza para mapear los datos de entrada recibidos en las
     * solicitudes HTTP a la entidad User que se almacenará en la base de datos.
     * 
     * @param request UserRequest - El objeto de solicitud que contiene los datos
     *                del nuevo usuario a crear
     * @return User - La entidad User creada a partir del UserRequest
     */
    public User fromRequest(UserRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    /**
     * Convierte una entidad User a un UserResponse. Toma los campos de la entidad
     * User y los asigna a un nuevo objeto UserResponse utilizando el patrón de
     * diseño Builder. Este método se utiliza para mapear los datos de la entidad
     * User a un formato de respuesta que se enviará en las respuestas HTTP. Incluye
     * el ID, el nombre completo, el correo electrónico, el número de teléfono y la
     * fecha de creación del usuario.
     * 
     * @param user User - La entidad User que se va a convertir a UserResponse
     * @return UserResponse - El objeto UserResponse creado a partir de la entidad
     *         User
     */
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
