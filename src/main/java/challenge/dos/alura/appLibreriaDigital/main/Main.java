package challenge.dos.alura.appLibreriaDigital.main;

import challenge.dos.alura.appLibreriaDigital.model.*;
import challenge.dos.alura.appLibreriaDigital.repository.AutorRepository;
import challenge.dos.alura.appLibreriaDigital.repository.LibroRepository;
import challenge.dos.alura.appLibreriaDigital.service.ConsumoAPI;
import challenge.dos.alura.appLibreriaDigital.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositoryLibro;
    private AutorRepository repositoryAutor;
    private List<Autor> autores;
    private List<Libro> libros;

    public Main(LibroRepository repositoryLibro, AutorRepository repositoryAutor) {
        this.repositoryLibro = repositoryLibro;
        this.repositoryAutor = repositoryAutor;
    }

    public void main() {
        menu();
    }

    private void menu() {
        int opcion;
        do {
            System.out.println("____________________________________________________");
            System.out.println(" 1. Buscar libro por título");
            System.out.println(" 2. Listar libros registrados");
            System.out.println(" 3. Listar autores registrados");
            System.out.println(" 4. Listar autores vivos en un determinado año");
            System.out.println(" 5. Listar libros por idioma");
            System.out.println(" 0. Salir");
            System.out.print(" Elija la opción a través de su número: ");
            opcion = scan.nextInt();

            while (opcion < 0 || opcion > 5) {
                System.out.print("Opción inválida, ingrese un número válido: ");
                opcion = scan.nextInt();
            }
            scan.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    autoresVivosPorAnio();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
            }
        } while (opcion != 0);
    }

    private DatosBusqueda getBusqueda() {
        System.out.println("Ha elegido buscar libro por título.\n");
        System.out.print("\nIntroduzca el nombre del libro: ");
        var nombreLibro = scan.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE +"?search="+ nombreLibro.replace(" ", "+"));
        DatosBusqueda datos = conversor.obtenerDatos(json, DatosBusqueda.class);
        return datos;
    }

    private void buscarLibroPorTitulo() {
        DatosBusqueda datosBusqueda = getBusqueda();
        if (datosBusqueda != null && !datosBusqueda.resultado().isEmpty()) {
            DatosLibros primerLibro = datosBusqueda.resultado().get(0);

            Libro libro = new Libro(primerLibro);
            Optional<Libro> libroExiste = repositoryLibro.findByTitulo(libro.getTitulo());
            if (libroExiste.isPresent()){
                System.out.println("\nLibro ya registrado, desplegando informacion...");
                System.out.println(libro);
            }else {

                if (!primerLibro.autor().isEmpty()) {
                    DatosAutor autor = primerLibro.autor().get(0);
                    Autor autor1 = new Autor(autor);
                    Optional<Autor> autorOptional = repositoryAutor.findByNombre(autor1.getNombre());

                    if (autorOptional.isPresent()) {
                        Autor autorExiste = autorOptional.get();
                        libro.setAutor(autorExiste);
                        repositoryLibro.save(libro);
                    } else {
                        Autor autorNuevo = repositoryAutor.save(autor1);
                        libro.setAutor(autorNuevo);
                        repositoryLibro.save(libro);
                    }

                    Integer numeroDescargas = libro.getNumero_descargas() != null ? libro.getNumero_descargas() : 0;
                    System.out.println("---------- Libro ----------");
                    System.out.printf("Titulo: %s\nAutor: %s\nIdioma: %s\nNumero de Descargas: %s",
                            libro.getTitulo(), autor1.getNombre(), libro.getLenguaje(), libro.getNumero_descargas());
                    System.out.println("\n----------------------------- \n");
                } else {
                    System.out.println("Sin autor [S/A]");
                }
            }
        } else {
            System.out.println("libro no encontrado");
        }
    }

    private void mostrarLibrosRegistrados() {
        System.out.println("Ha elegido listar libros registrados.\n");
        libros = repositoryLibro.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void mostrarAutoresRegistrados() {
        System.out.println("Ha elegido listar autores registrados.");
        autores = repositoryAutor.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void autoresVivosPorAnio() {
        System.out.println("Ha elegido listar autores vivos en un determinado año.\n");
        System.out.println("Ingrese el año de el(los) autor(es) que desea buscar continuaban con vida: ");
        var anio = scan.nextInt();
        autores = repositoryAutor.listaAutoresVivosPorAnio(anio);
        autores.stream()
                .forEach(System.out::println);
    }

    private List<Libro> datosBusquedaLenguaje(String idioma){
        var dato = Idioma.fromString(idioma);
        System.out.println("Lenguaje buscado: " + dato);

        List<Libro> libroPorIdioma = repositoryLibro.findByLenguaje(dato);
        return libroPorIdioma;
    }

    private void buscarLibroPorIdioma(){
        System.out.println("Ha elegido listar libros por idioma.\n");

        var opcion = -1;
        while (opcion != 0) {
            System.out.println("1. en -> Ingles");
            System.out.println("2. es -> Español");
            System.out.println("3. fr -> Francés");
            System.out.println("4. pt -> Portugués");
            System.out.println("0. Volver a Las opciones anteriores");
            System.out.print("Seleccione el idioma que desea buscar: ");

            while (!scan.hasNextInt()) {
                System.out.println("Opción inválida, ingrese un número válido: ");
                scan.nextLine();
            }
            opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                case 1:
                    List<Libro> librosEnIngles = datosBusquedaLenguaje("[en]");
                    if(librosEnIngles.isEmpty()) {
                        System.out.println("----------------------------------------------------\n" + "No contamos con libros disponibles en dicho idioma"+ "\n----------------------------------------------------\n");
                    }else {
                        librosEnIngles.forEach(System.out::println);
                    }
                    break;
                case 2:
                    List<Libro> librosEnEspanol = datosBusquedaLenguaje("[es]");
                    if(librosEnEspanol.isEmpty()) {
                        System.out.println("----------------------------------------------------\n" + "No contamos con libros disponibles en dicho idioma"+ "\n----------------------------------------------------\n");
                    }else {
                        librosEnEspanol.forEach(System.out::println);
                    }
                    break;
                case 3:
                    List<Libro> librosEnFrances = datosBusquedaLenguaje("[fr]");
                    if(librosEnFrances.isEmpty()) {
                        System.out.println("----------------------------------------------------\n" + "No contamos con libros disponibles en dicho idioma"+ "\n----------------------------------------------------\n");
                    }else {
                        librosEnFrances.forEach(System.out::println);
                    }
                    break;
                case 4:
                    List<Libro> librosEnPortugues = datosBusquedaLenguaje("[pt]");
                    if(librosEnPortugues.isEmpty()) {
                        System.out.println("----------------------------------------------------\n" + "No contamos con libros disponibles en dicho idioma"+ "\n----------------------------------------------------\n");
                    }else{
                        librosEnPortugues.forEach(System.out::println);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Seleccione un idioma");
            }
        }
    }

}

