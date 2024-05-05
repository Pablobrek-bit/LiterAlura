package br.com.henrique.pablo.LiterAlura.Service;

import br.com.henrique.pablo.LiterAlura.Model.BookRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T converteDados(String json, Class<T> classe){
        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results");

            resultsNode = resultsNode.get(0);

            System.out.println(resultsNode.toString());

            return mapper.readValue(resultsNode.toString(), classe);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
