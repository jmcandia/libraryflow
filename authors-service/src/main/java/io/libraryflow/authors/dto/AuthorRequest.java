package io.libraryflow.authors.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorRequest {
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    private String fullName;

    private String biography;

    @NotBlank(message = "Nationality is required")
    @Size(max = 100, message = "Nationality must be at most 100 characters")
    private String nationality;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
}
