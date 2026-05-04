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

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorResponse> getAllAuthors() {
        log.info("Fetching all authors");
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    public AuthorResponse getAuthorById(Long id) {
        log.info("Fetching author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        return authorMapper.toResponse(author);
    }

    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        log.info("Creating new author: {}", authorRequest.getFullName());
        Author author = authorRepository.save(authorMapper.toEntity(authorRequest));
        return authorMapper.toResponse(author);
    }

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

    public void deleteAuthor(Long id) {
        log.info("Deleting author with id: {}", id);
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
