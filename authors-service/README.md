# Servicio `authors-service`

Microservicio encargado de la gestión de autores dentro del sistema. Permite registrar, consultar, actualizar y eliminar autores, así como administrar la información asociada a sus obras.

## Descripción

Este microservicio es responsable de centralizar la lógica de negocio relacionada con la entidad **Author**, permitiendo desacoplar la gestión de autores de otros dominios como libros o publicaciones.

## Responsabilidades

- Registro de autores
- Consulta de autores
- Actualización de información de autores
- Eliminación de autores
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
      2. Artifact: `authors-service`
      3. Package name: `io.libraryflow.authors`
      4. Packaging: `Jar`
      5. Configuration: `Properties`
      6. Java: `17`
3. Incorporar las dependencias indicadas en el punto anterior.

**Usando `Spring CLI`:**

```bash
spring init --build=maven --language=java --boot-version=4.0.6 --group-id=io.libraryflow --artifact-id=authors-service --package-name=io.libraryflow.authors --packaging=jar --java-version=17 --dependencies=web,spring-webclient,validation,data-jpa,flyway,mysql,lombok authors-service
```

## Entidades

### Author

| Campo         | Tipo            | Descripción                |
|:--------------|:----------------|:---------------------------|
| `id`          | `Long`          | Identificador único        |
| `fullName`    | `String`        | Nombre completo            |
| `biography`   | `String`        | Biografía                  |
| `nationality` | `String`        | Correo electrónico (único) |
| `birthDate`   | `LocalDate`     | Fecha de nacimiento        |
| `createdAt`   | `LocalDateTime` | Fecha de creación          |

## Estructura del Proyecto

```plain
authors-service/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── io/libraryflow/authors/
│   │           ├── controller/
│   │           │   └── AuthorController.java
│   │           ├── dto/
│   │           │   ├── ApiErrorResponse.java
│   │           │   ├── AuthorRequest.java
│   │           │   └── AuthorResponse.java
│   │           ├── exception/
│   │           │   └── GlobalExceptionHandler.java
│   │           ├── mapper/
│   │           │   └── AuthorMapper.java
│   │           ├── model/
│   │           │   └── Author.java
│   │           ├── repository/
│   │           │   └── AuthorRepository.java
│   │           ├── service/
│   │           │   └── AuthorService.java
│   │           └── AuthorsServiceApplication.java
│   └── resources/
│       ├── db/migration/
│       │   ├── V1__create_table_authors.sql
│       │   └── V2__initial_data.sql
│       └── application.properties
└── pom.xml
```

## Endpoints

| Método   | Ruta            | Descripción                  |
|:---------|:----------------|:-----------------------------|
| `GET`    | `/authors`      | Obtiene todos los autores    |
| `GET`    | `/authors/{id}` | Obtiene un autor por su ID   |
| `POST`   | `/authors`      | Crea un nuevo autor          |
| `PUT`    | `/authors/{id}` | Actualiza un autor por su ID |
| `DELETE` | `/authors/{id}` | Elimina un autor por su ID   |
