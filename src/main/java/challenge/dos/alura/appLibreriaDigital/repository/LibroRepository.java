package challenge.dos.alura.appLibreriaDigital.repository;


import challenge.dos.alura.appLibreriaDigital.model.Idioma;
import challenge.dos.alura.appLibreriaDigital.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByLenguaje(Idioma idioma);

    Optional<Libro> findByTitulo(String titulo);

}