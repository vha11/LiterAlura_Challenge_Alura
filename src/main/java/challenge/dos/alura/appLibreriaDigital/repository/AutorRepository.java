package challenge.dos.alura.appLibreriaDigital.repository;

import challenge.dos.alura.appLibreriaDigital.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Método para encontrar un autor por su nombre
    Optional<Autor> findByNombre(String nombre);

    // Método para obtener una lista de autores vivos en un año específico
    @Query("SELECT a FROM Autor a WHERE a.anio_nacimiento <= :anio AND a.anio_defuncion >= :anio")
    List<Autor> listaAutoresVivosPorAnio(Integer anio);
}
