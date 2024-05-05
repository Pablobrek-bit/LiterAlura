package br.com.henrique.pablo.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authors;

    @Enumerated(EnumType.STRING)
    private Languages language;

    public Book( String title, Author authors, Languages language) {
        this.title = title;
        this.authors = authors;
        this.language = language;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthors() {
        return authors;
    }

    public void setAuthors(Author authors) {
        this.authors = authors;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", language=" + language;

    }
}
