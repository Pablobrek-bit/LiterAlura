package br.com.henrique.pablo.LiterAlura.Service;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeAPI {
    private static final String API_URL = "https://gutendex.com/books?search=";

    public static String consumeAPI(String urlPassed){
        Book book = new Book();

        urlPassed = urlPassed.replace(" ", "+");
        var url = API_URL + urlPassed;
        System.out.println(url);
        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpRequest request =
                HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();

        return json;
    }
}
