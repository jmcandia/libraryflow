package io.libraryflow.authors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para el servicio de autores.
 * Esta clase se encarga de iniciar la aplicación y configurar el contexto de
 * Spring.
 */
@SpringBootApplication
public class AuthorsServiceApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot. Este método es el
	 * punto de entrada de la aplicación y se encarga de lanzar el contexto de
	 * Spring, lo que permite que se configuren y se inicien todos los componentes
	 * necesarios para el funcionamiento del servicio de autores.
	 * 
	 * @param args String[] - Argumentos de línea de comandos que se pueden pasar al
	 *             iniciar la aplicación. Estos argumentos pueden ser utilizados
	 *             para configurar aspectos específicos de la aplicación, como el
	 *             puerto del servidor, el perfil de Spring activo, entre otros. Sin
	 *             embargo, en la mayoría de los casos, no se requieren argumentos
	 *             para iniciar la aplicación, y se pueden omitir al ejecutar el
	 *             comando de inicio.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthorsServiceApplication.class, args);
	}

}
