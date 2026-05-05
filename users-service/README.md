# Servicio `users-service`

Microservicio encargado de la gestión de usuarios dentro del sistema. Proporciona funcionalidades para la creación, consulta, actualización y eliminación de usuarios, además de servir como fuente de verdad para la información relacionada a estos.

## Descripción

Este microservicio forma parte de una arquitectura basada en microservicios y se encarga exclusivamente del manejo de la entidad **User**. Su objetivo es centralizar la lógica de negocio asociada a usuarios, garantizando escalabilidad, mantenibilidad y desacoplamiento del sistema.

## Responsabilidades

- Registro de usuarios
- Obtención de información de usuarios
- Actualización de datos de usuario
- Eliminación de usuarios
- Validación de datos
- Exposición de endpoints REST

## Dependencias

- Spring Web
- Reactive HTTP Client
- Validation
- Spring Data JPA
- MySQL
- Flayway Migration
- Lombok

## Inicialización del proyecto

**Usando `Spring Initializr`:**

1. Ir a [start.spring.io](https://start.spring.io)
2. Configurar las siguientes opciones:
   1. Project: `Maven`
   2. Language: `Java`
   3. Spring Boot: `4.0.6`
   4. Project Metadata:
      1. Group: `io.libraryflow`
      2. Artifact: `users-service`
      3. Package name: `io.libraryflow.users`
      4. Packaging: `Jar`
      5. Configuration: `Properties`
      6. Java: `17`
3. Incorporar las dependencias indicadas en el punto anterior.

**Usando `Spring CLI`:**

```bash
spring init --build=maven --language=java --boot-version=4.0.6 --group-id=io.libraryflow --artifact-id=users-service --package-name=io.libraryflow.users --packaging=jar --java-version=17 --dependencies=web,spring-webclient,validation,data-jpa,flyway,mysql,lombok users-service
```

## Entidades

### User

| Campo       | Tipo            | Descripción                     |
|:----------  |:----------------|:--------------------------------|
| `id`        | `Long`          | Identificador único del usuario |
| `fullName`  | `String`        | Nombre completo del usuario     |
| `email`     | `String`        | Correo electrónico (único)      |
| `phone`     | `String`        | Número de teléfono del usuario  |
| `createdAt` | `LocalDateTime` | Fecha de creación               |

## Estructura del Proyecto

```plain
users-service/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── io/libraryflow/users/
│   │           ├── controller/
│   │           │   └── UserController.java
│   │           ├── dto/
│   │           │   ├── ApiErrorResponse.java
│   │           │   ├── UserRequest.java
│   │           │   └── UserResponse.java
│   │           ├── exception/
│   │           │   └── GlobalExceptionHandler.java
│   │           ├── mapper/
│   │           │   └── UserMapper.java
│   │           ├── model/
│   │           │   └── User.java
│   │           ├── repository/
│   │           │   └── UserRepository.java
│   │           ├── service/
│   │           │   └── UserService.java
│   │           └── UsersServiceApplication.java
│   └── resources/
│       ├── db/migration/
│       │   ├── V1__create_table_users.sql
│       │   └── V2__initial_data.sql
│       └── application.properties
└── pom.xml
```

## Endpoints

| Método   | Ruta          | Descripción                    |
|:---------|:--------------|:-------------------------------|
| `GET`    | `/users`      | Obtiene todos los usuarios     |
| `GET`    | `/users/{id}` | Obtiene un usuario por su ID   |
| `POST`   | `/users`      | Crea un nuevo usuario          |
| `PUT`    | `/users/{id}` | Actualiza un usuario por su ID |
| `DELETE` | `/users/{id}` | Elimina un usuario por su ID   |
