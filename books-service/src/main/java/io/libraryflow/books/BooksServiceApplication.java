package io.libraryflow.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para el servicio de libros. Esta
 * clase se encarga de iniciar la aplicación y configurar el contexto de Spring.
 */
@SpringBootApplication
public class BooksServiceApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot. Este método es el
	 * punto de entrada de la aplicación y se encarga de lanzar el contexto de
	 * Spring, lo que permite que se configuren los beans y se inicie el servidor
	 * web para manejar las solicitudes HTTP.
	 * 
	 * @param args Argumentos de línea de comandos (no se utilizan en esta
	 *             aplicación, pero se pueden pasar si es necesario para
	 *             configuraciones específicas).
	 */
	public static void main(String[] args) {
		SpringApplication.run(BooksServiceApplication.class, args);
	}

}
