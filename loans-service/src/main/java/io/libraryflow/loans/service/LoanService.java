package io.libraryflow.loans.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.libraryflow.loans.dto.LoanRequest;
import io.libraryflow.loans.dto.LoanResponse;
import io.libraryflow.loans.mapper.LoanMapper;
import io.libraryflow.loans.model.Loan;
import io.libraryflow.loans.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase LoanService es una clase de servicio en la capa de negocio que
 * maneja la lógica relacionada con los préstamos. Proporciona métodos para
 * obtener todos los préstamos, obtener un préstamo por su ID, crear un nuevo
 * préstamo y marcar un préstamo como devuelto. Utiliza el LoanRepository para
 * interactuar con la base de datos y el LoanMapper para convertir entre las
 * entidades de préstamo y los objetos de transferencia de datos (DTO)
 * LoanRequest y LoanResponse.
 */
@Service
@Slf4j
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanMapper loanMapper;

    /**
     * Obtiene la lista de todos los préstamos desde el repositorio, mapea cada
     * préstamo a un LoanResponse utilizando el LoanMapper y devuelve la lista de
     * LoanResponse. Si no hay préstamos, devuelve una lista vacía.
     * 
     * @return List<LoanResponse> - La lista de préstamos mapeados a LoanResponse, o
     *         una lista vacía si no hay préstamos
     */
    public List<LoanResponse> getAllLoans() {
        log.info("Fetching all loans");
        return loanRepository.findAll().stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene la lista de préstamos asociados a un ID de usuario específico desde
     * el repositorio, mapea cada préstamo a un LoanResponse utilizando el
     * LoanMapper y devuelve la lista de LoanResponse. Si no hay préstamos para el
     * ID de usuario dado, devuelve una lista vacía.
     * 
     * @param userId Long - El ID del usuario para el cual se desean obtener los
     *               préstamos
     * @return List<LoanResponse> - La lista de préstamos mapeados a LoanResponse
     *         para el ID de usuario dado, o una lista vacía si no hay préstamos
     *         para ese ID de usuario
     */
    public List<LoanResponse> getLoansByUserId(Long userId) {
        log.info("Fetching loans for user id: {}", userId);
        return loanRepository.findByUserId(userId).stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene la lista de préstamos asociados a un ID de libro específico desde el
     * repositorio, mapea cada préstamo a un LoanResponse utilizando el LoanMapper y
     * devuelve la lista de LoanResponse. Si no hay préstamos para el ID de libro
     * dado, devuelve una lista vacía.
     * 
     * @param bookId Long - El ID del libro para el cual se desean obtener los
     *               préstamos
     * @return List<LoanResponse> - La lista de préstamos mapeados a LoanResponse
     *         para el ID de libro dado, o una lista vacía si no hay préstamos para
     *         ese ID de libro
     */
    public List<LoanResponse> getLoansByBookId(Long bookId) {
        log.info("Fetching loans for book id: {}", bookId);
        return loanRepository.findByBookId(bookId).stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene un préstamo por su ID desde el repositorio, mapea el préstamo a un
     * LoanResponse utilizando el LoanMapper y devuelve el LoanResponse. Si el
     * préstamo no se encuentra, lanza una excepción de tipo NoSuchElementException.
     * 
     * @param id Long - El ID del préstamo a obtener
     * @return LoanResponse - El préstamo mapeado a LoanResponse
     * @throws NoSuchElementException - Si el préstamo no se encuentra con el ID
     *                                dado
     */
    public LoanResponse getLoanById(Long id) {
        log.info("Fetching loan with id: {}", id);
        return loanRepository.findById(id)
                .map(loanMapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + id));
    }

    /**
     * Crea un nuevo préstamo a partir de un LoanRequest, guarda el préstamo en el
     * repositorio y devuelve el préstamo creado mapeado a LoanResponse. Si ocurre
     * un error durante la creación del préstamo, se registra el error y se lanza
     * una excepción.
     * 
     * @param loanRequest LoanRequest - El objeto que contiene los datos para crear
     *                    el nuevo préstamo
     * @return LoanResponse - El préstamo creado mapeado a LoanResponse
     */
    public LoanResponse createLoan(LoanRequest loanRequest) {
        log.info("Creating new loan for user id: {} and book id: {}", loanRequest.getUserId(), loanRequest.getBookId());
        Loan loan = loanMapper.fromRequest(loanRequest);
        // Establecer la fecha del préstamo al momento de la creación
        loan.setLoanDate(LocalDateTime.now());
        // Guardar el préstamo en el repositorio
        loan = loanRepository.save(loan);
        return loanMapper.toResponse(loan);
    }

    /**
     * Marca un préstamo como devuelto estableciendo la fecha de devolución al
     * momento de la devolución, guarda el préstamo actualizado en el repositorio y
     * devuelve el préstamo actualizado mapeado a LoanResponse. Si el préstamo no se
     * encuentra con el ID dado, se lanza una excepción de tipo
     * NoSuchElementException.
     * 
     * @param id Long - El ID del préstamo a marcar como devuelto
     * @return LoanResponse - El préstamo actualizado mapeado a LoanResponse
     * @throws NoSuchElementException - Si el préstamo no se encuentra con el ID
     * @throws IllegalStateException  - Si el préstamo ya ha sido devuelto
     */
    public LoanResponse returnLoan(Long id) {
        log.info("Returning loan with id: {}", id);
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + id));
        if (loan.getReturnDate() != null) {
            log.warn("Loan with id: {} is already returned", id);
            throw new IllegalStateException("Loan with id: " + id + " is already returned");
        }
        // Establecer la fecha de devolución al momento de la devolución
        loan.setReturnDate(LocalDateTime.now());
        // Guardar el préstamo actualizado en el repositorio
        loan = loanRepository.save(loan);
        return loanMapper.toResponse(loan);
    }
}
