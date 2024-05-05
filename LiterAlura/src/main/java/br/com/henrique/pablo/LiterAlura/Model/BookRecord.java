package br.com.henrique.pablo.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRecord(Integer id,
                         String title,
                         List<AuthorRecord> authors,
                         List<String> languages) {
}
