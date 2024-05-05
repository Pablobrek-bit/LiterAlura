package br.com.henrique.pablo.LiterAlura.Repository;

import br.com.henrique.pablo.LiterAlura.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

    @Query("select a from Author a where a.birthYear <= :year and (a.deathYear >= :year" +
            " or a.deathYear is null)")
    List<Author> findAuthorAlive(int year);
}
