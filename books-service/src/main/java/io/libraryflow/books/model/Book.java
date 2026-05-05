package io.libraryflow.books.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase Book es una entidad JPA que representa un libro en la base de datos.
 * Contiene campos para el ID del libro, título, resumen, ISBN, ID del autor,
 * disponibilidad y fecha de creación. La anotación @Entity indica que esta
 * clase es una entidad JPA, y la anotación @Table especifica el nombre de la
 * tabla en la base de datos. Los campos están anotados con @Column para definir
 * las propiedades de las columnas en la tabla. La anotación @Id indica que el
 * campo id es la clave primaria, y @GeneratedValue especifica que el valor del
 * ID se generará automáticamente. El método onCreate() se ejecuta antes de que
 * la entidad se persista en la base de datos y establece la fecha de creación
 * al momento actual.
 */
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String summary;

    @Column(unique = true, length = 20, nullable = false)
    private String isbn;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private Boolean available;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
