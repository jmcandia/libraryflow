package io.libraryflow.books.mapper;

import org.springframework.stereotype.Component;

import io.libraryflow.books.dto.AuthorResponse;
import io.libraryflow.books.dto.BookRequest;
import io.libraryflow.books.dto.BookResponse;
import io.libraryflow.books.model.Book;

/**
 * La clase BookMapper es un componente de Spring que se encarga de mapear
 * entre las entidades de libro (Book) y los objetos de transferencia de datos
 * (DTO) BookRequest y BookResponse. Proporciona métodos para convertir un
 * BookRequest a una entidad Book y para convertir una entidad Book a un
 * BookResponse. Este mapeo es útil para separar la lógica de negocio de la
 * representación de los datos en las solicitudes y respuestas HTTP, permitiendo
 * una mayor flexibilidad y mantenibilidad en el código.
 */
@Component
public class BookMapper {

    /**
     * Convierte un BookRequest a una entidad Book. Toma los campos del BookRequest
     * y los asigna a un nuevo objeto Book utilizando el patrón de diseño Builder.
     * El ID y la fecha de creación no se establecen en este método, ya que se
     * generarán automáticamente al persistir la entidad en la base de datos. Este
     * método se utiliza para mapear los datos de entrada recibidos en las
     * solicitudes HTTP a la entidad Book que se almacenará en la base de datos.
     * 
     * @param request BookRequest - El objeto de solicitud que contiene los datos
     *                del nuevo libro a crear
     * @return Book - La entidad Book creada a partir del BookRequest
     */
    public Book toEntity(BookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .summary(request.getSummary())
                .isbn(request.getIsbn())
                .authorId(request.getAuthorId())
                .available(request.getAvailable())
                .build();
    }

    /**
     * Convierte una entidad Book a un BookResponse. Toma los campos de la entidad
     * Book y los asigna a un nuevo objeto BookResponse utilizando el patrón de
     * diseño Builder. Este método se utiliza para mapear los datos de la entidad
     * Book a un objeto de transferencia de datos que se enviará en las respuestas
     * HTTP.
     *
     * @param book Book - La entidad Book que se desea convertir
     * @return BookResponse - El objeto de transferencia de datos creado a partir
     *         de la entidad Book
     */
    public BookResponse toResponse(Book book, AuthorResponse author) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .summary(book.getSummary())
                .isbn(book.getIsbn())
                .author(author)
                .available(book.getAvailable())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
