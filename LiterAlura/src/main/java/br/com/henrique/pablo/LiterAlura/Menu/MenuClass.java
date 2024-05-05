package br.com.henrique.pablo.LiterAlura.Menu;

import br.com.henrique.pablo.LiterAlura.Model.Author;
import br.com.henrique.pablo.LiterAlura.Model.Book;
import br.com.henrique.pablo.LiterAlura.Model.BookRecord;
import br.com.henrique.pablo.LiterAlura.Model.Languages;
import br.com.henrique.pablo.LiterAlura.Repository.AuthorRepository;
import br.com.henrique.pablo.LiterAlura.Repository.BookRepository;
import br.com.henrique.pablo.LiterAlura.Service.ConsumeAPI;
import br.com.henrique.pablo.LiterAlura.Service.ConverteDados;

import java.util.Scanner;

public class MenuClass {

    private final Scanner cop = new Scanner(System.in);
    private ConverteDados converteDados = new ConverteDados();

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public MenuClass(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu(){
        var option = -1;
        while(option != 0){
            var menu = """
                    1 - Buscar livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Exit
                    """;
            System.out.println(menu);
            option = cop.nextInt();
            cop.nextLine();
            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listBookRegister();
                    break;
                case 3:
                    listAuthorsRegister();
                    break;
                case 4:
                    listAuthorsAliveByYear();
                    break;
                case 5:
                    listBooksByIdiom();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }




    private void searchBookByTitle() {
        System.out.println("Enter the book title: ");
        var title = cop.nextLine();
        var jsonResponse = ConsumeAPI.consumeAPI(title);

        BookRecord response = converteDados.converteDados(jsonResponse, BookRecord.class);
        Author author = response.authors().stream()
                .findFirst().map(authorRecord -> new Author(authorRecord.name(), authorRecord.birthYear(), authorRecord.deathYear()))
                .orElse(null);

        Book book = new Book(response.title(), author,
                Languages.valueOf(response.languages().get(0).toUpperCase()));

        var bookAlreadyExists = bookRepository.findByTitle(book.getTitle());

        if (bookAlreadyExists != null){
            System.out.println("Book already registered");
            return;
        }

        assert author != null;

        authorRepository.save(author);
        bookRepository.save(book);
    }

    private void listBookRegister() {
        var books = bookRepository.findAll();

        if(books.isEmpty()){
            System.out.println("No books found");
            return;
        }

        books.forEach(System.out::println);
    }

    private void listAuthorsRegister() {
        var authors = authorRepository.findAll();

        if (authors.isEmpty()){
            System.out.println("No authors found");
            return;
        }

        authors.forEach(System.out::println);
    }

    private void listAuthorsAliveByYear() {
        System.out.println("Enter the year: ");
        var year = cop.nextInt();
        var authors = authorRepository.findAuthorAlive(year);

        if(authors.isEmpty()){
            System.out.println("No authors found");
            return;
        }

        authors.forEach(System.out::println);
    }

    private void listBooksByIdiom() {
        System.out.println("Enter the language: ");
        var language = cop.nextLine();
        var books =
                bookRepository.findByLanguage(Languages.valueOf(language.toUpperCase()));

        if (books.isEmpty()){
            System.out.println("No books found");
            return;
        }

        books.forEach(System.out::println);
    }

}
