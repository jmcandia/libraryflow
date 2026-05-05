package io.libraryflow.loans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.libraryflow.loans.model.Loan;

/**
 * La interfaz LoanRepository es un repositorio de Spring Data JPA que
 * proporciona métodos para interactuar con la base de datos para la entidad
 * Loan. Al extender JpaRepository, hereda métodos para realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas. El tipo
 * de entidad es Loan y el tipo de ID es Long. Esta interfaz se utiliza en la
 * capa de servicio para acceder a los datos de los préstamos almacenados en la
 * base de datos.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    /**
     * Encuentra todos los préstamos asociados a un usuario específico. Este método
     * se utiliza para obtener una lista de préstamos realizados por un usuario en
     * particular, identificando al usuario mediante su ID. Devuelve una lista de
     * objetos Loan que representan los préstamos realizados por el usuario
     * especificado.
     * 
     * @param userId Long - El ID del usuario para el cual se desean obtener los
     *               préstamos.
     * @return List<Loan> - Una lista de objetos Loan que representan los préstamos
     *         realizados por el usuario especificado.
     */
    List<Loan> findByUserId(Long userId);

    /**
     * Encuentra todos los préstamos asociados a un libro específico. Este método se
     * utiliza para obtener una lista de préstamos relacionados con un libro en
     * particular, identificando al libro mediante su ID. Devuelve una lista de
     * objetos Loan que representan los préstamos relacionados con el libro
     * especificado.
     * 
     * @param bookId Long - El ID del libro para el cual se desean obtener los
     *               préstamos.
     * @return List<Loan> - Una lista de objetos Loan que representan los préstamos
     *         relacionados con el libro especificado.
     */
    List<Loan> findByBookId(Long bookId);
}
