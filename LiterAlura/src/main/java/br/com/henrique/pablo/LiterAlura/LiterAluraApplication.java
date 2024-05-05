package br.com.henrique.pablo.LiterAlura;

import br.com.henrique.pablo.LiterAlura.Menu.MenuClass;
import br.com.henrique.pablo.LiterAlura.Repository.AuthorRepository;
import br.com.henrique.pablo.LiterAlura.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuClass menuClass = new MenuClass(bookRepository, authorRepository);
		menuClass.showMenu();
	}
}
