package io.libraryflow.authors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.libraryflow.authors.model.Author;

/**
 * La interfaz AuthorRepository es un repositorio de Spring Data JPA que
 * proporciona métodos para interactuar con la base de datos para la entidad
 * Author. Al extender JpaRepository, hereda métodos para realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas. El tipo
 * de entidad es Author y el tipo de ID es Long. Esta interfaz se utiliza en la
 * capa de servicio para acceder a los datos de los autores almacenados en la
 * base de datos.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
