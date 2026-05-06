package io.libraryflow.users.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {
    private Long id;
    private BookResponse book;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
}
