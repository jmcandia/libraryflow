package io.libraryflow.authors.mapper;

import org.springframework.stereotype.Component;

import io.libraryflow.authors.dto.AuthorRequest;
import io.libraryflow.authors.dto.AuthorResponse;
import io.libraryflow.authors.model.Author;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequest request) {
        return Author.builder()
                .fullName(request.getFullName())
                .biography(request.getBiography())
                .nationality(request.getNationality())
                .birthDate(request.getBirthDate())
                .build();
    }

    public AuthorResponse toResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .biography(author.getBiography())
                .nationality(author.getNationality())
                .birthDate(author.getBirthDate())
                .createdAt(author.getCreatedAt())
                .build();
    }
}
