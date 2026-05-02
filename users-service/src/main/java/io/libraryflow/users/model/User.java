package io.libraryflow.users.model;

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
 * La clase User es una entidad JPA que representa a un usuario en la base de
 * datos.
 * Contiene campos para el ID del usuario, nombre completo, correo electrónico,
 * número de teléfono y la fecha de creación. La anotación @Entity indica que
 * esta clase es una entidad JPA, y la anotación @Table especifica el nombre de
 * la tabla en la base de datos. Los campos están anotados con @Column para
 * definir las propiedades de las columnas en la tabla. La anotación @Id indica
 * que el campo id es la clave primaria, y @GeneratedValue especifica que el
 * valor del ID se generará automáticamente. El método onCreate() se ejecuta
 * antes de que la entidad se persista en la base de datos y establece la fecha
 * de creación al momento actual.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
