package br.com.henrique.pablo.LiterAlura.Model;

public enum Languages {
    PT("pt"),
    EN("en"),
    ES("es"),
    FR("fr"),
    DE("de");

    private final String language;

    Languages(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
