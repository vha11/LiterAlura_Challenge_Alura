package challenge.dos.alura.appLibreriaDigital.model;

import jakarta.persistence.*;
import java.util.List;




@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer anio_nacimiento;
    private Integer anio_defuncion;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre=datosAutor.nombre();
        this.anio_nacimiento = datosAutor.anio_nacimiento();
        this.anio_defuncion = datosAutor.anio_defuncion();
    }

    @Override
    public String toString() {
        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");

        for(int i = 0; i < libro.size() ; i++) {
            librosStr.append(libro.get(i).getTitulo());
            if (i < libro.size() - 1 ){
                librosStr.append(", ");
            }
        }
        return String.format("---------- Autor ---------- \nNombre:" +
                " %s\n%s\nFecha de Nacimiento: %s\nFecha de Defuncion:" +
                " %s\n----------------------------- \n",nombre,librosStr.toString(),anio_nacimiento,anio_defuncion);


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getanio_nacimiento() {
        return anio_nacimiento;
    }

    public void setanio_nacimiento(Integer anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public Integer getanio_defuncion() {
        return anio_defuncion;
    }

    public void setanio_defuncion(Integer anio_defuncion) {
        this.anio_defuncion = anio_defuncion;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }
}