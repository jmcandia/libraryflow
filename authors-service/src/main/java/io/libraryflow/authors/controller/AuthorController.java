package io.libraryflow.authors.controller;

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

import io.libraryflow.authors.dto.AuthorRequest;
import io.libraryflow.authors.dto.AuthorResponse;
import io.libraryflow.authors.service.AuthorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase AuthorController es un controlador REST en la capa de presentación
 * que maneja las solicitudes HTTP relacionadas con los autores. Proporciona
 * métodos para obtener la lista de autores, obtener un autor por su ID, crear
 * un nuevo autor, actualizar un autor existente y eliminar un autor. Utiliza el
 * AuthorService para delegar la lógica de negocio y devuelve respuestas HTTP
 * adecuadas para cada operación. Además, utiliza el registro de eventos
 * (logging) para registrar las solicitudes entrantes y las acciones realizadas.
 */
@RestController
@RequestMapping("/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * Obtiene la lista de autores desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo de la lista de autores y un código de estado 200 OK.
     * 
     * @return ResponseEntity<List<AuthorResponse>> - La respuesta HTTP con la lista
     *         de autores y el código de estado 200 OK
     */
    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAuthors() {
        log.info("GET /authors");
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    /**
     * Obtiene un autor por su ID desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo del autor y un código de estado 200 OK. Si el autor no se
     * encuentra, se lanza una excepción que será manejada por el controlador de
     * excepciones global.
     * 
     * @param id Long - El ID del autor a obtener
     * @return ResponseEntity<AuthorResponse> - La respuesta HTTP con el autor y el
     *         código de estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        log.info("GET /authors/{}", id);
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    /**
     * Crea un nuevo autor utilizando los datos proporcionados en el cuerpo de la
     * solicitud. Valida el cuerpo de la solicitud utilizando la anotación @Valid y
     * delega la creación del autor al AuthorService. Luego, construye una URI para
     * el nuevo recurso creado utilizando ServletUriComponentsBuilder y devuelve una
     * respuesta HTTP con el código de estado 201 Created y el cuerpo del autor
     * creado.
     * 
     * @param author AuthorRequest - El objeto que contiene los datos del autor a
     *               crear, validado con @Valid
     * @return ResponseEntity<AuthorResponse> - La respuesta HTTP con el autor y el
     *         código de estado 201 Created
     */
    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest author) {
        log.info("POST /authors");
        AuthorResponse createAuthor = authorService.createAuthor(author);
        // Crea una URI para el nuevo recurso creado utilizando
        // ServletUriComponentsBuilder, que construye la URI basada en la URL actual de
        // la solicitud y el ID del nuevo autor creado.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createAuthor.getId())
                .toUri();
        return ResponseEntity.created(location).body(createAuthor);
    }

    /**
     * Actualiza un autor existente utilizando los datos proporcionados en el cuerpo
     * de la solicitud. Valida el cuerpo de la solicitud utilizando la
     * anotación @Valid y delega la actualización del autor al AuthorService. Luego,
     * devuelve una respuesta HTTP con el cuerpo del autor actualizado y un código
     * de estado 200 OK. Si el autor no se encuentra, se lanza una excepción que
     * será manejada por el controlador de excepciones global.
     * 
     * @param id     Long - El ID del autor a actualizar, obtenido de la ruta de la
     *               solicitud
     * @param author AuthorRequest - El objeto que contiene los datos del autor a
     *               actualizar, validado con @Valid
     * @return ResponseEntity<AuthorResponse> - La respuesta HTTP con el autor
     *         actualizado y el código de estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id,
            @Valid @RequestBody AuthorRequest author) {
        log.info("PUT /authors/{}", id);
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }

    /**
     * Elimina un autor por su ID utilizando el AuthorService y devuelve una
     * respuesta HTTP con un código de estado 204 No Content. Si el autor no se
     * encuentra, se lanza una excepción que será manejada por el controlador de
     * excepciones global.
     * 
     * @param id Long - El ID del autor a eliminar, obtenido de la ruta de la
     *           solicitud
     * @return ResponseEntity<Void> - La respuesta HTTP con el código de estado 204
     *         No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.info("DELETE /authors/{}", id);
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
