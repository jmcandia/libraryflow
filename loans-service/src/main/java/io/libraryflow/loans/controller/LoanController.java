package io.libraryflow.loans.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.libraryflow.loans.dto.LoanRequest;
import io.libraryflow.loans.dto.LoanResponse;
import io.libraryflow.loans.service.LoanService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/loans")
@Slf4j
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Obtiene la lista de préstamos desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo de la lista de préstamos y un código de estado 200 OK.
     * 
     * @return ResponseEntity<List<LoanResponse>> - La respuesta HTTP con la lista
     *         de préstamos y el código de estado 200 OK
     */
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getLoans() {
        log.info("GET /loans");
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    /**
     * Obtiene la lista de préstamos de un usuario específico por su ID desde el
     * servicio y devuelve una respuesta HTTP con el cuerpo de la lista de préstamos
     * y un código de estado 200 OK.
     * 
     * @param userId Long - El ID del usuario para el cual se desean obtener los
     *               préstamos
     * @return ResponseEntity<List<LoanResponse>> - La respuesta HTTP con la lista
     *         de préstamos del usuario y el código de estado 200 OK
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanResponse>> getLoansByUser(@PathVariable Long userId) {
        log.info("GET /loans/user/{}", userId);
        return ResponseEntity.ok(loanService.getLoansByUserId(userId));
    }

    /**
     * Obtiene la lista de préstamos de un libro específico por su ID desde el
     * servicio y devuelve una respuesta HTTP con el cuerpo de la lista de préstamos
     * y un código de estado 200 OK.
     * 
     * @param bookId Long - El ID del libro para el cual se desean obtener los
     *               préstamos
     * @return ResponseEntity<List<LoanResponse>> - La respuesta HTTP con la lista
     *         de préstamos del libro y el código de estado 200 OK
     */
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanResponse>> getLoansByBook(@PathVariable Long bookId) {
        log.info("GET /loans/book/{}", bookId);
        return ResponseEntity.ok(loanService.getLoansByBookId(bookId));
    }

    /**
     * Obtiene un préstamo específico por su ID desde el servicio y devuelve una
     * respuesta HTTP con el cuerpo del préstamo y un código de estado 200 OK.
     * 
     * @param id Long - El ID del préstamo a obtener
     * @return ResponseEntity<LoanResponse> - La respuesta HTTP con el préstamo
     *         y el código de estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable Long id) {
        log.info("GET /loans/{}", id);
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    /**
     * Crea un nuevo préstamo utilizando el servicio y devuelve una respuesta HTTP
     * con el cuerpo del préstamo creado y un código de estado 201 Created. Además,
     * se crea una URI para el nuevo recurso creado utilizando
     * ServletUriComponentsBuilder, que construye la URI basada en la solicitud
     * actual y el ID del nuevo recurso creado, y se incluye en el encabezado
     * Location de la respuesta.
     * 
     * @param loanRequest LoanRequest - La solicitud de préstamo a crear
     * @return ResponseEntity<LoanResponse> - La respuesta HTTP con el préstamo
     *         creado y el código de estado 201 Created
     */
    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanRequest loanRequest) {
        log.info("POST /loans");
        LoanResponse createdLoan = loanService.createLoan(loanRequest);
        // Crea una URI para el nuevo recurso creado utilizando
        // ServletUriComponentsBuilder, que construye la URI basada en la solicitud
        // actual y el ID del nuevo recurso creado.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLoan.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdLoan);
    }

    /**
     * Marca un préstamo como devuelto utilizando el servicio y devuelve una
     * respuesta HTTP con el cuerpo del préstamo actualizado y un código de estado
     * 200 OK.
     * 
     * @param id Long - El ID del préstamo a marcar como devuelto
     * @return ResponseEntity<LoanResponse> - La respuesta HTTP con el préstamo
     *         actualizado y el código de estado 200 OK
     */
    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnLoan(@PathVariable Long id) {
        log.info("PATCH /loans/{}/return", id);
        LoanResponse updatedLoan = loanService.returnLoan(id);
        return ResponseEntity.ok(updatedLoan);
    }
}
