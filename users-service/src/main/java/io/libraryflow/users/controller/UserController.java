package io.libraryflow.users.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.libraryflow.users.dto.UserRequest;
import io.libraryflow.users.dto.UserResponse;
import io.libraryflow.users.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase UserController es un controlador REST en la capa de presentación que
 * maneja las solicitudes HTTP relacionadas con los usuarios. Proporciona
 * métodos para obtener la lista de usuarios, obtener un usuario por su ID,
 * crear un nuevo usuario, actualizar un usuario existente y eliminar un
 * usuario. Utiliza el UserService para delegar la lógica de negocio y devuelve
 * respuestas HTTP adecuadas para cada operación. Además, utiliza el registro
 * de eventos (logging) para registrar las solicitudes entrantes y las acciones
 * realizadas.
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Obtiene la lista de usuarios desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo de la lista de usuarios y un código de estado 200 OK.
     * 
     * @return ResponseEntity<List<UserResponse>> - La respuesta HTTP con la lista
     *         de usuarios y el código de estado 200 OK
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        log.info("GET /users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Obtiene un usuario por su ID desde el servicio y devuelve una respuesta HTTP
     * con el cuerpo del usuario y un código de estado 200 OK. Si el usuario no se
     * encuentra, se lanza una excepción que será manejada por el controlador de
     * excepciones global.
     * 
     * @param id Long - El ID del usuario a obtener
     * @return ResponseEntity<UserResponse> - La respuesta HTTP con el usuario y el
     *         código de estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.info("GET /users/{}", id);
        // Obtiene un usuario por su ID desde el servicio y devuelve una respuesta HTTP
        // con el cuerpo del usuario y un código de estado 200 OK. Si el usuario no se
        // encuentra, se lanza una excepción que será manejada por el controlador de
        // excepciones global.
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Crea un nuevo usuario utilizando el servicio y devuelve una respuesta HTTP
     * con el cuerpo del usuario creado y un código de estado 201 Created. Además,
     * se incluye un encabezado Location que apunta a la URL del nuevo recurso
     * creado.
     * 
     * @param userRequest UserRequest - El objeto de solicitud que contiene los
     *                    datos del nuevo usuario a crear
     * @return ResponseEntity<UserResponse> - La respuesta HTTP con el usuario
     *         creado, el código de estado 201 Created y el encabezado Location con
     *         la URL del nuevo recurso creado
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("POST /users");
        UserResponse createdUser = userService.createUser(userRequest);
        // Crea una URI para el nuevo recurso creado utilizando
        // ServletUriComponentsBuilder, que construye la URI basada en la URL actual de
        // la solicitud y el ID del nuevo usuario creado.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

    /**
     * Actualiza un usuario existente utilizando el servicio y devuelve una
     * respuesta HTTP con el cuerpo del usuario actualizado y un código de estado
     * 200 OK. Si el usuario no se encuentra, se lanza una excepción que será
     * manejada por el controlador de excepciones global.
     * 
     * @param id
     * @param userRequest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        log.info("PUT /users/{}", id);
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    /**
     * Elimina un usuario existente utilizando el servicio y devuelve una respuesta
     * HTTP con un código de estado 204 No Content. Si el usuario no se encuentra,
     * se lanza una excepción que será manejada por el controlador de excepciones
     * global.
     * 
     * @param id Long - El ID del usuario a eliminar
     * @return ResponseEntity<Void> - La respuesta HTTP con el código de estado 204
     *         No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("DELETE /users/{}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
