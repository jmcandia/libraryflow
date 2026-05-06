package io.libraryflow.books.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.libraryflow.books.dto.BookRequest;
import io.libraryflow.books.dto.BookResponse;
import io.libraryflow.books.mapper.BookMapper;
import io.libraryflow.books.model.Book;
import io.libraryflow.books.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase BookService es un servicio de Spring que proporciona la lógica de
 * negocio para la entidad Book. Contiene métodos para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas sobre los
 * libros almacenados en la base de datos. Utiliza BookRepository para
 * interactuar con la base de datos y BookMapper para convertir entre las
 * entidades Book y los objetos de transferencia de datos (DTO) BookRequest y
 * BookResponse.
 */
@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    /**
     * Obtiene la lista de todos los libros desde el repositorio, mapea cada libro a
     * un BookResponse utilizando el BookMapper y devuelve la lista de BookResponse.
     * Si no hay libros, devuelve una lista vacía.
     * 
     * @return List<BookResponse> - La lista de libros mapeados a BookResponse, o
     *         una lista vacía si no hay libros
     */
    public List<BookResponse> getAllBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene la lista de libros asociados a un autor específico por su ID desde el
     * repositorio, mapea cada libro a un BookResponse utilizando el BookMapper y
     * devuelve la lista de BookResponse. Si no hay libros para el autor dado,
     * devuelve una lista vacía.
     * 
     * @param authorId Long - El ID del autor cuyos libros se desean obtener
     * @return List<BookResponse> - La lista de libros asociados al autor mapeados a
     *         BookResponse, o una lista vacía si no hay libros para el autor dado
     */
    public List<BookResponse> getBooksByAuthor(Long authorId) {
        log.info("Fetching books for author with id: {}", authorId);
        return bookRepository.findByAuthorId(authorId).stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene un libro por su ID desde el repositorio, mapea el libro a un
     * BookResponse utilizando el BookMapper y devuelve el BookResponse. Si el libro
     * no se encuentra, lanza una excepción de tipo NoSuchElementException.
     * 
     * @param id Long - El ID del libro a obtener
     * @return BookResponse - El libro mapeado a BookResponse
     * @throws NoSuchElementException - Si el libro no se encuentra con el ID dado
     */
    public BookResponse getBookById(Long id) {
        log.info("Fetching book with id: {}", id);
        return bookRepository.findById(id)
                .map(bookMapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
    }

    /**
     * Obtiene la lista de libros asociados a un autor específico por su ID desde el
     * repositorio, mapea cada libro a un BookResponse utilizando el BookMapper y
     * devuelve la lista de BookResponse. Si no hay libros para el autor dado,
     * devuelve una lista vacía.
     * 
     * @param authorId Long - El ID del autor cuyos libros se desean obtener
     * @return List<BookResponse> - La lista de libros asociados al autor mapeados a
     *         BookResponse, o una lista vacía si no hay libros para el autor dado
     */
    public List<BookResponse> getBooksByAuthorId(Long authorId) {
        log.info("Fetching books for author with id: {}", authorId);
        return bookRepository.findByAuthorId(authorId).stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    /**
     * Crea un nuevo libro a partir de un BookRequest, guarda el libro en el
     * repositorio y devuelve el BookResponse correspondiente. Si ocurre algún error
     * durante la creación, lanza una excepción.
     * 
     * @param bookRequest BookRequest - Los datos del libro a crear
     * @return BookResponse - El libro creado mapeado a BookResponse
     */
    public BookResponse createBook(BookRequest bookRequest) {
        log.info("Creating new book: {}", bookRequest.getTitle());
        Book book = bookMapper.toEntity(bookRequest);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    /**
     * Actualiza un libro existente por su ID utilizando los datos de un
     * BookRequest, guarda el libro actualizado en el repositorio y devuelve el
     * BookResponse correspondiente. Si el libro no se encuentra, lanza una
     * excepción.
     * 
     * @param id          Long - El ID del libro a actualizar
     * @param bookRequest BookRequest - Los datos del libro a actualizar
     * @return BookResponse - El libro actualizado mapeado a BookResponse
     */
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        log.info("Updating book with id: {}", id);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setSummary(bookRequest.getSummary());
        existingBook.setIsbn(bookRequest.getIsbn());
        existingBook.setAuthorId(bookRequest.getAuthorId());
        existingBook.setAvailable(bookRequest.getAvailable());
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toResponse(updatedBook);
    }

    /**
     * Elimina un libro por su ID. Si el libro no se encuentra, lanza una excepción
     * de tipo NoSuchElementException. Si ocurre algún error durante la eliminación,
     * lanza una excepción.
     * 
     * @param id Long - El ID del libro a eliminar
     * @throws NoSuchElementException - Si el libro no se encuentra con el ID dado
     */
    public void deleteBook(Long id) {
        log.info("Deleting book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    /**
     * Marca un libro como prestado estableciendo su disponibilidad a false, guarda
     * el libro actualizado en el repositorio y devuelve el BookResponse
     * correspondiente. Si el libro no se encuentra, lanza una excepción de tipo
     * NoSuchElementException. Si el libro ya está prestado (disponibilidad false),
     * lanza una excepción de tipo IllegalStateException.
     * 
     * @param id Long - El ID del libro a prestar
     * @return BookResponse - El libro prestado mapeado a BookResponse
     */
    public BookResponse loanBook(Long id) {
        log.info("Loaning book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        if (!book.getAvailable()) {
            throw new IllegalStateException("Book with id: " + id + " is not available for loan");
        }
        book.setAvailable(false);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }

    /**
     * Marca un libro como devuelto estableciendo su disponibilidad a true, guarda
     * el libro actualizado en el repositorio y devuelve el BookResponse
     * correspondiente. Si el libro no se encuentra, lanza una excepción de tipo
     * NoSuchElementException. Si el libro ya está disponible (disponibilidad true),
     * lanza una excepción de tipo IllegalStateException.
     * 
     * @param id Long - El ID del libro a devolver
     * @return BookResponse - El libro devuelto mapeado a BookResponse
     */
    public BookResponse returnBook(Long id) {
        log.info("Returning book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        if (book.getAvailable()) {
            throw new IllegalStateException("Book with id: " + id + " is not currently loaned out");
        }
        book.setAvailable(true);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }
}
