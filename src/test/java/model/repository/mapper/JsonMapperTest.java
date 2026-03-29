package model.repository.mapper;

import model.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Constants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {
    private JsonMapper jsonMapper = new JsonMapper();

    @BeforeEach
    void setUp() {
        jsonMapper = new JsonMapper();
    }

    @Test
    void mapQuestionsReturnsCorrectList() {
        List<Question> questions = jsonMapper.mapQuestions(Constants.QUESTIONS_FILE_NAME);

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(1L, questions.get(0).getId());
    }

    @Test
    void mapQuestionsThrowsExceptionWhenFileNotFound() {
        assertThrows(RuntimeException.class, () -> jsonMapper.mapQuestions("nonExistentJsonFile.json"));
    }

}