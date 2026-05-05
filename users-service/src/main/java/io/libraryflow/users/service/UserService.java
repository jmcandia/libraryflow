package io.libraryflow.users.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.libraryflow.users.dto.UserRequest;
import io.libraryflow.users.dto.UserResponse;
import io.libraryflow.users.mapper.UserMapper;
import io.libraryflow.users.model.User;
import io.libraryflow.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * La clase UserService es una clase de servicio en la capa de negocio que
 * maneja la lógica relacionada con los usuarios. Proporciona métodos para
 * obtener todos los usuarios, obtener un usuario por su ID, crear un nuevo
 * usuario, actualizar un usuario existente y eliminar un usuario. Utiliza el
 * UserRepository para interactuar con la base de datos y el UserMapper para
 * convertir entre las entidades de usuario y los objetos de transferencia de
 * datos (DTO) UserRequest y UserResponse.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Obtiene la lista de todos los usuarios desde el repositorio, mapea cada
     * usuario a un UserResponse utilizando el UserMapper y devuelve la lista de
     * UserResponse. Si no hay usuarios, devuelve una lista vacía.
     * 
     * @return List<UserResponse> - La lista de usuarios mapeados a UserResponse, o
     *         una lista vacía si no hay usuarios
     */
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene un usuario por su ID desde el repositorio, mapea el usuario a un
     * UserResponse utilizando el UserMapper y devuelve el UserResponse. Si el
     * usuario no se encuentra, lanza una excepción de tipo NoSuchElementException.
     * 
     * @param id Long - El ID del usuario a obtener
     * @return UserResponse - El usuario mapeado a UserResponse
     * @throws NoSuchElementException - Si el usuario no se encuentra con el ID dado
     */
    public UserResponse getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**
     * Crea un nuevo usuario a partir de un UserRequest, guarda el usuario en el
     * repositorio, mapea el usuario guardado a un UserResponse utilizando el
     * UserMapper y devuelve el UserResponse. Si ocurre algún error durante la
     * creación del usuario, se lanza una excepción.
     * 
     * @param userRequest UserRequest - El objeto que contiene los datos para crear
     *                    el nuevo usuario
     * @return UserResponse - El usuario creado mapeado a UserResponse
     */
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating new user");
        User user = userRepository.save(userMapper.fromRequest(userRequest));
        return userMapper.toResponse(user);
    }

    /**
     * Actualiza un usuario existente identificado por su ID utilizando los datos
     * proporcionados en un UserRequest, guarda el usuario actualizado en el
     * repositorio, mapea el usuario actualizado a un UserResponse utilizando el
     * UserMapper y devuelve el UserResponse. Si el usuario no se encuentra con el
     * ID dado, se lanza una excepción de tipo NoSuchElementException.
     * 
     * @param id          Long - El ID del usuario a actualizar
     * @param userRequest UserRequest - El objeto que contiene los datos para
     *                    actualizar el usuario
     * @return UserResponse - El usuario actualizado mapeado a UserResponse
     * @throws NoSuchElementException - Si el usuario no se encuentra con el ID dado
     */
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        log.info("Updating user with id: {}", id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        existingUser.setFullName(userRequest.getFullName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhone(userRequest.getPhone());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponse(updatedUser);
    }

    /**
     * Elimina un usuario existente identificado por su ID del repositorio.
     * Si el usuario no se encuentra con el ID dado, se lanza una excepción de tipo
     * NoSuchElementException.
     *
     * @param id Long - El ID del usuario a eliminar
     * @throws NoSuchElementException - Si el usuario no se encuentra con el ID dado
     */
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
