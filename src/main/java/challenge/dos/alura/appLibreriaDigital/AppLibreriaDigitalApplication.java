package challenge.dos.alura.appLibreriaDigital;

import challenge.dos.alura.appLibreriaDigital.main.Main;
import challenge.dos.alura.appLibreriaDigital.repository.AutorRepository;
import challenge.dos.alura.appLibreriaDigital.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppLibreriaDigitalApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;
	public static void main(String[] args) {
		SpringApplication.run(AppLibreriaDigitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(libroRepository,autorRepository);
		main.main();

	}
}
