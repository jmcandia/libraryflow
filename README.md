# LibraryFlow - Sistema de Gestión de Biblioteca

Bienvenidos a **LibraryFlow**, una biblioteca digital que necesita modernizar su sistema utilizando una arquitectura basada en microservicios.

Actualmente, la biblioteca gestiona manualmente a sus usuarios, libros y préstamos, lo que genera problemas como duplicidad de registros, préstamos inconsistentes y falta de trazabilidad.

Tu misión será diseñar e implementar una solución backend basada en microservicios utilizando **Java + Spring Boot**, aplicando buenas prácticas de desarrollo, arquitectura y comunicación entre servicios.

## Propósito

El propósito de este ejercicio es verificar la capacidad del equipo para diseñar, construir y justificar una arquitectura distribuida basada en microservicios independientes, con persistencia real, reglas de negocio claras, validaciones robustas y manejo adecuado de errores, demostrando dominio en:

- La estructuración de proyectos bajo el patrón CSR.
- La manipulación de base de datos.
- El manejo adecuado de validaciones y errores.
- El desarrollo de endpoints REST completos y funcionales.
- El cumplimiento de buenas prácticas de organización y calidad de código.
- La implementación de herramientas de trabajo colaborativo.
- La integración de manejo de respuestas eficientes.

## Criterios de Evaluación

- Persistencia real con `JPA` + `Hibernate`:
  - Implementación de entidades con anotaciones como `@Entity`, `@Id`, `@GeneratedValue`, `@OneToMany`, `@ManyToOne`, etc.
  - Uso de repositorios mediante `JpaRepository` para ejecutar operaciones **CRUD** reales.
  - Configuración del `datasource` y dialecto en `application.properties`.
  - Creación y/o ejecución de migraciones iniciales de base de datos (scripts SQL o Flyway/Liquibase).
- Modelado de datos y normalización básica:
  - Diseño de modelos relacionales coherentes con el dominio del proyecto.
  - Definición de relaciones correctas entre tablas y entidades.
  - Manejo de claves primarias, claves foráneas e integridad referencial.
- Validaciones con `Bean Validation` (JSR 380):
  - Uso de anotaciones.
  - Validación de datos en controladores, retornando respuestas consistentes ante entradas inválidas.
  - Separación entre **DTOs** y entidades para validar datos de manera limpia y segura.
- Manejo de excepciones básicas y respuestas controladas:
  - Uso de `try/catch` cuando corresponda en la capa de servicio.
  - Retorno de respuestas coherentes con `ResponseEntity`, utilizando códigos **HTTP** adecuados.
  - Uso de `@ControllerAdvice` para manejo centralizado de errores.
- Estructuración del código bajo el patrón CSR:
  - Separación clara entre:
    - `Controller` → manejo de solicitudes **REST**.
    - `Service` → lógica de negocio.
    - `Repository` → acceso a datos.
  - Flujo ordenado, coherente y sin mezclar responsabilidades.
  - Uso de **DTOs** para comunicación entre capas cuando sea apropiado.
- Respuestas **REST** correctas y consistentes
  - Todos los endpoints deben retornar **JSON** estructurado.
  - Los microservicios deben aplicar buenos principios **REST**:
    - rutas semánticas.
    - métodos **HTTP** adecuados (`GET`, `POST`, `PUT`, `DELETE`).
    - parámetros, path variables y request bodies organizados.
    - Uso obligatorio de `ResponseEntity` para controlar la respuesta.
- Integración de logs estructurados con **SLF4J**:
  - Mensajes claros que permitan trazabilidad entre capas.
  - Reflejo de eventos relevantes como creación, actualización, errores y validaciones fallidas.
- Comunicación entre microservicios:
  - Consumo de endpoints entre microservicios mediante `WebClient` o `Feign Client`.
  - Manejo de timeouts, errores y validación de datos recibidos.
  - Pruebas de integración mínimas mediante **Postman** u otra herramienta **REST**.
- Buenas prácticas y calidad de código:
  - Código comentado cuando sea necesario.
  - Nombres de clases, métodos y variables significativos.
  - Estructura limpia y modular del proyecto.
  - Uso adecuado de paquetes según responsabilidad.
  - Eliminación de código muerto o duplicado antes de la entrega.
- **Integración de herramientas de trabajo colaborativo y gestión de versiones como Git**.

## Entregables

- Repositorio Git con:
  - Código fuente de los microservicios
  - `README` principal
- Colección de **Postman** o similar
- Scripts de base de datos o migraciones
- Evidencia de pruebas

## Resultado Esperado

Al finalizar este ejercicio, deberías tener un sistema funcional que:

- Cumpla principios de arquitectura de microservicios
- Aplique buenas prácticas de desarrollo backend
- Mantenga consistencia en reglas de negocio
- Sea escalable, mantenible y bien estructurado

## Composición del ejercicio

El sistema debe estar compuesto por los siguientes microservicios:

### 1. Microservicio `users-service`

Responsable de la gestión de usuarios.

**Funcionalidades mínimas:**

- Crear usuario
- Obtener usuario por ID
- Listar usuarios
- Eliminar usuario
- Listar todos los préstamos de un usuario

**Atributos sugeridos para la entidad `User`:**

- `id`: identificador único del usuario en el sistema
- `fullName`: nombre completo del usuario
- `email`: correo electrónico del usuario
- `phone`: número de teléfono del usuario
- `createdAt`: fecha en que el usuario fue registrado en el sistema

### 2. Microservicio `authors-service`

Responsable de la gestión de autores.

**Funcionalidades mínimas:**

- Crear autor
- Obtener autor por ID
- Listar autores
- Eliminar autor
- Listar todos los libros por autor

**Atributos sugeridos para la entidad `Author`:**

- `id`: identificador único del autor
- `fullName`: nombre completo del autor
- `biography`: biografía del autor
- `nationality`: nacionalidad del autor
- `birthDate`: fecha de nacimiento del autor
- `createdAt`: fecha en que el autor fue registrado en el sistema

### 3. Microservicio `books-service`

Responsable del catálogo de libros.

> ⚠️ **Consideración importante:** Cada libro tiene una sola copia disponible, por lo que no se debe implementar manejo de inventario.

**Funcionalidades mínimas:**

- Crear libro
- Obtener libro por ID
- Obtener libro por ISBN
- Listar libros
- Eliminar libro
- Listar todos los préstamos por libro

**Atributos sugeridos para la entidad `Book`:**

- `id`: identificador único del libro
- `title`: título del libro
- `sumarry`: sinopsis del libro
- `isbn`: código internacional único que identifica al libro
- `authorId`: identificador del autor del libro
- `available`: indica si el libro está disponible para préstamo (true) o actualmente prestado (false)
- `createdAt`: fecha en que el libro fue registrado en el sistema

### 4. Microservicio `loans-service`

Responsable de la gestión de préstamos.

**Funcionalidades mínimas:**

- Registrar préstamo
- Obtener préstamo por ID
- Listar todos los préstamos
- Devolver libro

**Reglas de negocio:**

- Un libro solo puede ser prestado si está disponible
- Un usuario puede tener múltiples préstamos
- Al registrar un préstamo:
  - Se debe validar que el usuario exista
  - Se debe validar que el libro exista y esté disponible
  - Se debe marcar el libro como no disponible
- Al devolver un libro:
  - Se debe marcar el libro como disponible nuevamente

**Atributos sugeridos para le entidad `Loan`:**

- `id`: identificador único del préstamo
- `userId`: identificador del usuario que realiza el préstamo
- `bookId`: identificador del libro prestado
- `loanDate`: fecha en que se realizó el préstamo
- `returnDate`: fecha en que se devuelve el libro (puede ser nula si aún no ha sido devuelto)

## Autor

José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)
