package io.libraryflow.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.libraryflow.users.model.User;

/**
 * La interfaz UserRepository es un repositorio de Spring Data JPA que
 * proporciona métodos para interactuar con la base de datos para la entidad
 * User. Al extender JpaRepository, hereda métodos para realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas. El tipo
 * de entidad es User y el tipo de ID es Long. Esta interfaz se utiliza en la
 * capa de servicio para acceder a los datos de los usuarios almacenados en la
 * base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
