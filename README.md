# LiterAlura

¡Hola a todos! Bienvenidos.
El proyecto es un catálogo de libros que permite registrar y consultar libros en una base de datos utilizando Java, Spring y PostgreSQL.

## Requisitos

Antes de comenzar, asegúrate de tener ciertos conocimientos sobre **Java** y **Spring Data JPA**. El proyecto involucra las siguientes funcionalidades básicas:

1. **Buscar libro por título**: La aplicación permite buscar un libro por su nombre. Si el libro no existe en la base de datos, se consultará una API externa para obtener la información y registrarlo.

2. **Listar libros registrados**: Muestra todos los libros almacenados en la base de datos.

3. **Listar autores registrados**: Muestra todos los autores que han escrito libros en la base de datos.

4. **Listar autores vivos en un año determinado**: Permite consultar qué autores estaban vivos en un año específico.

5. **Listar libros por idioma**: Filtra los libros según el idioma (ES, EN, FR, PT).

### Manejo de Errores

- **Libro no encontrado**: Muestra un mensaje si el libro no se encuentra en la base de datos o si la búsqueda en la API no devuelve resultados.
- **Evitar duplicados**: Impide que el mismo libro se registre más de una vez en la base de datos.

## Tecnologías Utilizadas

- **Java**
- **Spring Boot**
- **PostgreSQL**

## Configuración del Proyecto

1. **Crear un proyecto en Spring Initializr**:
   - Accede a [Spring Initializr](https://start.spring.io).
   - Configura el proyecto como **Maven** en **Java**.
   - Utiliza la última versión estable de **Spring Boot** (la versión que use fue la 3.3.1).
   - El **packaging** debe ser **JAR**.
   - La versión de **Java** es la 17.
   - Añade las dependencias **Spring Data JPA** y **PostgreSQL Driver**.

2. **API para consultar libros**:
   - Utiliza la API **Gutendex**, disponible en [gutendex.com](https://gutendex.com), basada en el proyecto Gutenberg.
   - Consulta la documentación para entender cómo realizar las consultas y manejar los datos.

---
