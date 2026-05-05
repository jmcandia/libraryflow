package io.libraryflow.authors.mapper;

import org.springframework.stereotype.Component;

import io.libraryflow.authors.dto.AuthorRequest;
import io.libraryflow.authors.dto.AuthorResponse;
import io.libraryflow.authors.model.Author;

/**
 * La clase AuthorMapper es un componente de Spring que se encarga de mapear
 * entre las entidades de autor (Author) y los objetos de transferencia de datos
 * (DTO) AuthorRequest y AuthorResponse. Proporciona métodos para convertir un
 * AuthorRequest a una entidad Author y para convertir una entidad Author a un
 * AuthorResponse. Este mapeo es útil para separar la lógica de negocio de la
 * representación de los datos en las solicitudes y respuestas HTTP, permitiendo
 * una mayor flexibilidad y mantenibilidad en el código.
 */
@Component
public class AuthorMapper {

    /**
     * Convierte un AuthorRequest a una entidad Author. Toma los campos del
     * AuthorRequest y los asigna a un nuevo objeto Author utilizando el patrón de
     * diseño Builder. El ID y la fecha de creación no se establecen en este método,
     * ya que se generarán automáticamente al persistir la entidad en la base de
     * datos. Este método se utiliza para mapear los datos de entrada recibidos en
     * las solicitudes HTTP a la entidad Author que se almacenará en la base de
     * datos.
     * 
     * @param request AuthorRequest - El objeto de solicitud que contiene los datos
     *                del nuevo autor a crear
     * @return Author - La entidad Author creada a partir del AuthorRequest
     */
    public Author toEntity(AuthorRequest request) {
        return Author.builder()
                .fullName(request.getFullName())
                .biography(request.getBiography())
                .nationality(request.getNationality())
                .birthDate(request.getBirthDate())
                .build();
    }

    /**
     * Convierte una entidad Author a un AuthorResponse. Toma los campos de la
     * entidad Author y los asigna a un nuevo objeto AuthorResponse utilizando el
     * patrón de diseño Builder. Este método se utiliza para mapear los datos de la
     * entidad Author a un objeto de transferencia de datos que se devolverá en las
     * respuestas HTTP.
     * 
     * @param author Author - La entidad Author que se desea convertir
     * @return AuthorResponse - El objeto de transferencia de datos creado a partir
     *         de la entidad Author
     */
    public AuthorResponse toResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .biography(author.getBiography())
                .nationality(author.getNationality())
                .birthDate(author.getBirthDate())
                .createdAt(author.getCreatedAt())
                .build();
    }
}
