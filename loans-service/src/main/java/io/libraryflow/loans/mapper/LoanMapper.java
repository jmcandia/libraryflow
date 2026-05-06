package io.libraryflow.loans.mapper;

import org.springframework.stereotype.Component;

import io.libraryflow.loans.dto.BookResponse;
import io.libraryflow.loans.dto.LoanRequest;
import io.libraryflow.loans.dto.LoanResponse;
import io.libraryflow.loans.dto.UserResponse;
import io.libraryflow.loans.model.Loan;

/**
 * La clase LoanMapper es un componente de Spring que se encarga de mapear entre
 * las entidades de préstamo (Loan) y los objetos de transferencia de datos
 * (DTO) LoanRequest y LoanResponse. Proporciona métodos para convertir un
 * LoanRequest a una entidad Loan y para convertir una entidad Loan a un
 * LoanResponse. Este mapeo es útil para separar la lógica de negocio de la
 * representación de los datos en las solicitudes y respuestas HTTP, permitiendo
 * una mayor flexibilidad y mantenibilidad en el código.
 */
@Component
public class LoanMapper {

    /**
     * Convierte un LoanRequest a una entidad Loan. Toma los campos del LoanRequest
     * y los asigna a un nuevo objeto Loan utilizando el patrón de diseño Builder.
     * El ID, la fecha de préstamo y la fecha de devolución no se establecen en este
     * método, ya que se generarán automáticamente al persistir la entidad en la
     * base de datos o se calcularán en función de la fecha de préstamo. Este método
     * se utiliza para mapear los datos de entrada recibidos en las solicitudes HTTP
     * a la entidad Loan que se almacenará en la base de datos.
     * 
     * @param request LoanRequest - El objeto de solicitud que contiene los datos
     *                del nuevo préstamo a crear
     * @return Loan - La entidad Loan creada a partir del LoanRequest
     */
    public Loan fromRequest(LoanRequest request) {
        return Loan.builder()
                .userId(request.getUserId())
                .bookId(request.getBookId())
                .build();
    }

    /**
     * Convierte una entidad Loan a un LoanResponse. Toma los campos de la entidad
     * Loan y los asigna a un nuevo objeto LoanResponse utilizando el patrón de
     * diseño Builder. Este método se utiliza para mapear los datos de la entidad
     * Loan a un formato de respuesta que se enviará en las respuestas HTTP. Incluye
     * el ID, el ID del usuario, el ID del libro, la fecha de préstamo y la fecha de
     * devolución del préstamo.
     * 
     * @param loan
     * @return
     */
    public LoanResponse toResponse(Loan loan, UserResponse user, BookResponse book) {
        return LoanResponse.builder()
                .id(loan.getId())
                .user(user)
                .book(book)
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .build();
    }
}
