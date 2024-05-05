package br.com.henrique.pablo.LiterAlura.Repository;

import br.com.henrique.pablo.LiterAlura.Model.Book;
import br.com.henrique.pablo.LiterAlura.Model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByLanguage(Languages language);

}
