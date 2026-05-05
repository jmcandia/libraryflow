package io.libraryflow.books.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.libraryflow.books.dto.BookRequest;
import io.libraryflow.books.dto.BookResponse;
import io.libraryflow.books.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase BookController es un controlador REST en la capa de presentación que
 * maneja las solicitudes HTTP relacionadas con los libros. Proporciona métodos
 * para obtener la lista de libros, obtener un libro por su ID, obtener libros
 * por el ID del autor, crear un nuevo libro, actualizar un libro existente y
 * eliminar un libro. Utiliza el BookService para delegar la lógica de negocio y
 * devuelve respuestas HTTP adecuadas para cada operación. Además, utiliza el
 * registro de eventos (logging) para registrar las solicitudes entrantes y las
 * acciones realizadas.
 */
@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Obtiene la lista de libros desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo de la lista de libros y un código de estado 200 OK.
     * 
     * @return ResponseEntity<List<BookResponse>> - La respuesta HTTP con la lista
     *         de libros y el código de estado 200 OK
     */
    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        log.info("GET /books");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Obtiene la lista de libros por el ID del autor desde el servicio y devuelve
     * una respuesta HTTP con el cuerpo de la lista de libros y un código de estado
     * 200 OK. Si el autor no se encuentra, se lanza una excepción que será manejada
     * por el controlador de excepciones global.
     * 
     * @param authorId
     * @return
     */
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(@PathVariable Long authorId) {
        log.info("GET /books/author/{}", authorId);
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorId));
    }

    /**
     * Obtiene un libro por su ID desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo del libro y un código de estado 200 OK. Si el libro no se
     * encuentra, se lanza una excepción que será manejada por el controlador de
     * excepciones global.
     * 
     * @param id Long - El ID del libro a obtener
     * @return ResponseEntity<BookResponse> - La respuesta HTTP con el libro y el
     *         código de estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        log.info("GET /books/{}", id);
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    /**
     * Crea un nuevo libro utilizando los datos proporcionados en el cuerpo de la
     * solicitud. Valida el cuerpo de la solicitud utilizando la anotación @Valid y
     * delega la creación del libro al BookService. Luego, construye una URI para
     * el nuevo recurso creado utilizando ServletUriComponentsBuilder y devuelve una
     * respuesta HTTP con el código de estado 201 Created y el cuerpo del libro
     * 
     * @param bookRequest BookRequest - El objeto que contiene los datos del libro a
     *                    crear, validado
     * @return ResponseEntity<BookResponse> - La respuesta HTTP con el libro y el
     *         código de estado 201 Created
     */
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        log.info("POST /books");
        BookResponse createdBook = bookService.createBook(bookRequest);
        // Crea una URI para el recurso recién creado utilizando
        // ServletUriComponentsBuilder, que construye la URI basada en la solicitud
        // actual y el ID del libro creado.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBook);
    }

    /**
     * Actualiza un libro existente utilizando los datos proporcionados en el cuerpo
     * de la solicitud. Valida el cuerpo de la solicitud utilizando la
     * anotación @Valid y delega la actualización del libro al BookService. Luego,
     * devuelve una respuesta HTTP con el cuerpo del libro actualizado y un código
     * de estado 200 OK. Si el libro no se encuentra, se lanza una excepción que
     * será manejada por el controlador de excepciones global.
     * 
     * @param id          Long - El ID del libro a actualizar, obtenido de la ruta
     *                    de la solicitud
     * @param bookRequest BookRequest - El objeto que contiene los datos del libro a
     *                    actualizar, validado con @Valid
     * @return ResponseEntity<BookResponse> - La respuesta HTTP con el libro
     *         actualizado y el código de estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id,
            @Valid @RequestBody BookRequest bookRequest) {
        log.info("PUT /books/{}", id);
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    /**
     * Elimina un libro por su ID utilizando el BookService. Luego, devuelve una
     * respuesta HTTP con un código de estado 204 No Content para indicar que la
     * eliminación fue exitosa. Si el libro no se encuentra, se lanza una excepción
     * que será manejada por el controlador de excepciones global.
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("DELETE /books/{}", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
