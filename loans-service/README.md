# Servicio `loans-service`

Microservicio encargado de la gestión de préstamos de libros dentro del sistema. Permite registrar, consultar, actualizar y finalizar préstamos, gestionando la relación entre préstamos y libros.

## Descripción

Este microservicio centraliza la lógica de negocio asociada a la entidad **Loan**, permitiendo controlar el ciclo de vida de un préstamo: creación, seguimiento y devolución.

Forma parte de una arquitectura de microservicios y se integra con los servicios de préstamos y libros mediante identificadores (`loanId`, `bookId`), manteniendo bajo acoplamiento.

## Responsabilidades

- Registro de préstamos
- Consulta de préstamos
- Finalización (devolución) de préstamos
- Validación de disponibilidad de libros (a nivel lógico)
- Validación de préstamos
- Control de fechas (inicio, devolución)
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
      2. Artifact: `loans-service`
      3. Package name: `io.libraryflow.loans`
      4. Packaging: `Jar`
      5. Configuration: `Properties`
      6. Java: `17`
3. Incorporar las dependencias indicadas en el punto anterior.

**Usando `Spring CLI`:**

```bash
spring init --build=maven --language=java --boot-version=4.0.6 --group-id=io.libraryflow --artifact-id=loans-service --package-name=io.libraryflow.loans --packaging=jar --java-version=17 --dependencies=web,spring-webclient,validation,data-jpa,flyway,mysql,lombok loans-service
```

## Entidades

### Loan

| Campo        | Tipo            | Descripción                        |
|:-------------|:----------------|:-----------------------------------|
| `id`         | `Long`          | Identificador único                |
| `userId`     | `Long`          | ID del préstamo (refrencia externa) |
| `bookId`     | `Long`          | ID del libro (refrencia externa)   |
| `loanDate`   | `LocalDateTime` | Fecha de creación                  |
| `returnDate` | `LocalDateTime` | Fecha de devolución                |

## Estructura del Proyecto

```plain
loans-service/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── io/libraryflow/loans/
│   │           ├── controller/
│   │           │   └── LoanController.java
│   │           ├── dto/
│   │           │   ├── ApiErrorResponse.java
│   │           │   ├── LoanRequest.java
│   │           │   └── LoanResponse.java
│   │           ├── exception/
│   │           │   └── GlobalExceptionHandler.java
│   │           ├── mapper/
│   │           │   └── LoanMapper.java
│   │           ├── model/
│   │           │   └── Loan.java
│   │           ├── repository/
│   │           │   └── LoanRepository.java
│   │           ├── service/
│   │           │   └── LoanService.java
│   │           └── LoansServiceApplication.java
│   └── resources/
│       ├── db/migration/
│       │   ├── V1__create_table_loans.sql
│       │   └── V2__initial_data.sql
│       └── application.properties
└── pom.xml
```

## Endpoints

| Método   | Ruta                 | Descripción                                          |
|:---------|:---------------------|:-----------------------------------------------------|
| `GET`    | `/loans`             | Obtiene todos los préstamos                          |
| `GET`    | `/loans/{id}`        | Obtiene un préstamo por su ID                        |
| `GET`    | `/loans/user/{id}`   | Obtiene todos los préstamos de un usuario específico |
| `GET`    | `/loans/book/{id}`   | Obtiene todos los préstamos de un libro específico   |
| `POST`   | `/loans`             | Crea un nuevo préstamo                               |
| `PATCH`  | `/loans/{id}/return` | Actualiza un préstamo por su ID                      |
