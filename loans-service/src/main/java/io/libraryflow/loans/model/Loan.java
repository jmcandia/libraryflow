package io.libraryflow.loans.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase Loan es una entidad JPA que representa un préstamo de libro en la
 * base de datos. Contiene campos para el ID del préstamo, ID del usuario, ID
 * del libro, fecha del préstamo y fecha de devolución. La anotación @Entity
 * indica que esta clase es una entidad JPA, y la anotación @Table especifica el
 * nombre de la tabla en la base de datos. Los campos están anotados con @Column
 * para definir las propiedades de las columnas en la tabla. La anotación @Id
 * indica que el campo id es la clave primaria, y @GeneratedValue especifica que
 * el valor del ID se generará automáticamente.
 */
@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "loan_date", nullable = false, updatable = false)
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;
}
