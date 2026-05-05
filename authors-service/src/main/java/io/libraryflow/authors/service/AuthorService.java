package io.libraryflow.authors.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.libraryflow.authors.dto.AuthorRequest;
import io.libraryflow.authors.dto.AuthorResponse;
import io.libraryflow.authors.mapper.AuthorMapper;
import io.libraryflow.authors.model.Author;
import io.libraryflow.authors.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase AuthorService es un servicio de Spring que proporciona la lógica de
 * negocio para la entidad Author. Contiene métodos para realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas sobre los
 * autores almacenados en la base de datos. Utiliza AuthorRepository para
 * interactuar con la base de datos y AuthorMapper para convertir entre las
 * entidades Author y los objetos de transferencia de datos (DTO) AuthorRequest
 * y AuthorResponse.
 */
@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    /**
     * Obtiene la lista de todos los autores desde el repositorio, mapea cada autor
     * a un AuthorResponse utilizando el AuthorMapper y devuelve la lista de
     * AuthorResponse. Si no hay autores, devuelve una lista vacía.
     * 
     * @return List<AuthorResponse> - La lista de autores mapeados a AuthorResponse,
     *         o una lista vacía si no hay autores
     */
    public List<AuthorResponse> getAllAuthors() {
        log.info("Fetching all authors");
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene un autor por su ID desde el repositorio, mapea el autor a un
     * AuthorResponse utilizando el AuthorMapper y devuelve el AuthorResponse. Si el
     * autor no se encuentra, lanza una excepción de tipo NoSuchElementException.
     * 
     * @param id Long - El ID del autor a obtener
     * @return AuthorResponse - El autor mapeado a AuthorResponse
     * @throws NoSuchElementException - Si el autor no se encuentra con el ID dado
     */
    public AuthorResponse getAuthorById(Long id) {
        log.info("Fetching author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        return authorMapper.toResponse(author);
    }

    /**
     * Crea un nuevo autor a partir de un AuthorRequest, guarda el autor en el
     * repositorio y devuelve el AuthorResponse correspondiente. Si ocurre algún
     * error durante la creación, lanza una excepción.
     * 
     * @param authorRequest AuthorRequest - El objeto de transferencia de datos que
     *                      contiene la información del autor a crear
     * @return AuthorResponse - El autor creado mapeado a AuthorResponse
     */
    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        log.info("Creating new author: {}", authorRequest.getFullName());
        Author author = authorRepository.save(authorMapper.toEntity(authorRequest));
        return authorMapper.toResponse(author);
    }

    /**
     * Actualiza un autor existente con el ID dado utilizando la información
     * proporcionada en el AuthorRequest. Si el autor no se encuentra, lanza una
     * excepción de tipo NoSuchElementException. Devuelve el AuthorResponse
     * actualizado. Si ocurre algún error durante la actualización, lanza una
     * excepción.
     * 
     * @param id            Long - El ID del autor a actualizar
     * @param authorRequest AuthorRequest - El objeto de transferencia de datos que
     *                      contiene la información actualizada del autor
     * @return AuthorResponse - El autor actualizado mapeado a AuthorResponse
     */
    public AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest) {
        log.info("Updating author with id: {}", id);
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));

        existingAuthor.setFullName(authorRequest.getFullName());
        existingAuthor.setBiography(authorRequest.getBiography());
        existingAuthor.setNationality(authorRequest.getNationality());
        existingAuthor.setBirthDate(authorRequest.getBirthDate());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.toResponse(updatedAuthor);
    }

    /**
     * Elimina un autor por su ID. Si el autor no se encuentra, lanza una excepción
     * de tipo NoSuchElementException. Si ocurre algún error durante la eliminación,
     * lanza una excepción.
     * 
     * @param id Long - El ID del autor a eliminar
     * @throws NoSuchElementException - Si el autor no se encuentra con el ID dado
     */
    public void deleteAuthor(Long id) {
        log.info("Deleting author with id: {}", id);
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
