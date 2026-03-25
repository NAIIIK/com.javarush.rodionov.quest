package model.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entity.Question;

import java.io.IOException;
import java.util.List;

public class JsonMapper {
    public List<Question> mapQuestions(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(
                    getClass().getClassLoader().getResourceAsStream(fileName),
                    new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load questions from the file: " + fileName, e);
        }
    }
}
