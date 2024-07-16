# Literalura

## Descripción

La aplicación **Literalura** es una aplicación de consola desarrollada en Java utilizando Spring Boot. Permite gestionar autores y libros, así como realizar búsquedas y listados de libros y autores. La aplicación interactúa con una base de datos para almacenar y recuperar información sobre autores y libros.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- `com.oracle.nexteducation.literalura.models`: Contiene las clases de modelo `Author` y `Book`.
- `com.oracle.nexteducation.literalura.repositories`: Contiene las interfaces de repositorio `AuthorRepository` y `BookRepository`.
- `com.oracle.nexteducation.literalura.services`: Contiene las clases de servicio `AuthorService`, `BookService` y `ApiClient`.
- `com.oracle.nexteducation.literalura`: Contiene la clase principal `Application`.

## Modelos

### Author

Representa un autor y contiene los siguientes campos:

- `id`: Identificador único del autor.
- `name`: Nombre del autor.
- `birthYear`: Año de nacimiento del autor.
- `deathYear`: Año de fallecimiento del autor.
- `books`: Lista de libros escritos por el autor.

### Book

Representa un libro y contiene los siguientes campos:

- `id`: Identificador único del libro.
- `title`: Título del libro.
- `language`: Idioma del libro.
- `downloadCount`: Número de descargas del libro.
- `author`: Autor del libro.

## Repositorios

### AuthorRepository

Extiende `JpaRepository` y proporciona métodos para:

- Buscar autores que estuvieron vivos en un año específico.

### BookRepository

Extiende `JpaRepository` y proporciona métodos para:

- Buscar libros por idioma.
- Contar libros por idioma.

## Servicios

### AuthorService

Proporciona métodos para:

- Agregar un nuevo autor.
- Obtener todos los autores.
- Obtener autores que estuvieron vivos en un año específico.

### BookService

Proporciona métodos para:

- Agregar un nuevo libro.
- Obtener todos los libros.
- Obtener libros por idioma.
- Contar libros por idioma.
- Obtener autores con sus libros.

## Clase Principal

### Application

La clase `Application` es la clase principal que inicia la aplicación Spring Boot. Implementa `CommandLineRunner` para proporcionar una interfaz de consola interactiva con las siguientes opciones:

1. Buscar un libro.
2. Listar todos los libros.
3. Listar libros por idioma.
4. Listar autores con sus libros.
5. Listar autores vivos en un año específico.
6. Salir.

## Configuración

El archivo `application.properties` en el directorio `resources` contiene la configuración de la aplicación.

## Ejecución

Para ejecutar la aplicación, utiliza el siguiente comando:

bash
mvn spring-boot:run


## Uso

Al iniciar la aplicación, se presentará un menú con las opciones disponibles. Sigue las instrucciones en pantalla para interactuar con la aplicación.

## Ejemplo de Uso

1. **Buscar un libro**: Ingresa una palabra clave para buscar libros que coincidan con esa palabra.
2. **Listar todos los libros**: Muestra una lista de todos los libros almacenados en la base de datos.
3. **Listar libros por idioma**: Ingresa un idioma para filtrar y mostrar los libros en ese idioma.
4. **Listar autores con sus libros**: Muestra una lista de autores junto con los libros que han escrito.
5. **Listar autores vivos en un año específico**: Ingresa un año para mostrar los autores que estuvieron vivos en ese año.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para contribuir a este proyecto.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

¡Gracias por usar la aplicación Literalura!
