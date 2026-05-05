# Servicio `books-service`

Microservicio encargado de la gestión de libros dentro del sistema. Permite registrar, consultar, actualizar y eliminar libros, además de manejar la relación con autores de forma desacoplada.

## Descripción

Este microservicio centraliza la lógica de negocio asociada a la entidad **Book**, permitiendo gestionar el catálogo de libros dentro de una arquitectura basada en microservicios.

El servicio está diseñado para integrarse con el microservicio de autores, manteniendo un bajo acoplamiento mediante referencias (por ejemplo, `authorId`).

## Responsabilidades

- Registro de libros
- Consulta de libros
- Actualización de información de libros
- Eliminación de libros
- Asociación de libros a autores (vía `authorId`)
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
      2. Artifact: `books-service`
      3. Package name: `io.libraryflow.books`
      4. Packaging: `Jar`
      5. Configuration: `Properties`
      6. Java: `17`
3. Incorporar las dependencias indicadas en el punto anterior.

**Usando `Spring CLI`:**

```bash
spring init --build=maven --language=java --boot-version=4.0.6 --group-id=io.libraryflow --artifact-id=books-service --package-name=io.libraryflow.books --packaging=jar --java-version=17 --dependencies=web,spring-webclient,validation,data-jpa,flyway,mysql,lombok books-service
```

## Entidades

### Book

| Campo       | Tipo            | Descripción                      |
|:----------  |:----------------|:---------------------------------|
| `id`        | `Long`          | Identificador único              |
| `title`     | `String`        | Título                           |
| `summary`   | `String`        | Resumen                          |
| `isbn`      | `String`        | Código internacional             |
| `authorId`  | `String`        | ID del autor (refrencia externa) |
| `available` | `Boolean`       | Disponibiliad                    |
| `createdAt` | `LocalDateTime` | Fecha de creación                |

## Estructura del Proyecto

```plain
books-service/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── io/libraryflow/books/
│   │           ├── controller/
│   │           │   └── BookController.java
│   │           ├── dto/
│   │           │   ├── ApiErrorResponse.java
│   │           │   ├── BookRequest.java
│   │           │   └── BookResponse.java
│   │           ├── exception/
│   │           │   └── GlobalExceptionHandler.java
│   │           ├── mapper/
│   │           │   └── BookMapper.java
│   │           ├── model/
│   │           │   └── Book.java
│   │           ├── repository/
│   │           │   └── BookRepository.java
│   │           ├── service/
│   │           │   └── BookService.java
│   │           └── BooksServiceApplication.java
│   └── resources/
│       ├── db/migration/
│       │   ├── V1__create_table_books.sql
│       │   └── V2__initial_data.sql
│       └── application.properties
└── pom.xml
```

## Enpoints

| Método   | Ruta                | Descripción                          |
|:---------|:--------------------|:-------------------------------------|
| `GET`    | `books`             | Obtiene todos los libros             |
| `GET`    | `books/author/{id}` | Obtiene todos los libros de un autor |
| `GET`    | `books/{id}`        | Obtiene un libro por su ID           |
| `POST`   | `books`             | Crea un nuevo libro                  |
| `PUT`    | `books/{id}`        | Actualiza un libro por su ID         |
| `DELETE` | `books/{id}`        | Elimina un libro por su ID           |
