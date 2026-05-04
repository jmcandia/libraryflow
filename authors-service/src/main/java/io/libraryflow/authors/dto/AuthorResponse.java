package io.libraryflow.authors.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponse {
    private Long id;
    private String fullName;
    private String biography;
    private String nationality;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
}
