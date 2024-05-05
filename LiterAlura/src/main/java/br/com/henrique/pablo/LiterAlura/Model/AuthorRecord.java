package br.com.henrique.pablo.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorRecord(String name,
                           @JsonAlias("birth_year") int birthYear,
                           @JsonAlias("death_year") int deathYear) {
}
