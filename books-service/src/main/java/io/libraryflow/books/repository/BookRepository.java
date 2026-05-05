package io.libraryflow.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.libraryflow.books.model.Book;

/**
 * La interfaz BookRepository es un repositorio de Spring Data JPA que
 * proporciona métodos para interactuar con la base de datos para la entidad
 * Book. Al extender JpaRepository, hereda métodos para realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas. El tipo
 * de entidad es Book y el tipo de ID es Long. Esta interfaz se utiliza en la
 * capa de servicio para acceder a los datos de los libros almacenados en la
 * base de datos.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Encuentra una lista de libros por el ID del autor. Este método se utiliza
     * para obtener todos los libros escritos por un autor específico. El método
     * devuelve una lista de objetos Book que coinciden con el ID del autor
     * proporcionado.
     * 
     * @param authorId Long - El ID del autor cuyos libros se desean encontrar
     * @return List<Book> - Una lista de libros escritos por el autor con el ID dado
     */
    List<Book> findByAuthorId(Long authorId);
}
