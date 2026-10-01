package alura.com.br.testefipe.services;

import alura.com.br.testefipe.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestefipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TestefipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibirMenu();

	}
}
