package model.repository;

import model.entity.Answer;
import model.entity.Question;
import model.repository.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import util.Constants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileQuestionRepositoryTest {
    private FileQuestionRepository repository;
    private final Long NON_EXISTENT_ID = -1L;

    @BeforeEach
    void setUp() {
        Mapper mapper = Mockito.mock(Mapper.class);

        Answer answer = new Answer();
        answer.setId(11L);

        Question question = new Question();
        question.setId(1L);
        question.setAnswers(List.of(answer));

        Mockito.when(mapper.mapQuestions(Constants.QUESTIONS_FILE_NAME))
                .thenReturn(List.of(question));

        repository = new FileQuestionRepository(Constants.QUESTIONS_FILE_NAME, mapper);
    }

    @Test
    void findQuestionByIdReturnsCorrectQuestion() {
        Question question = repository.findQuestionById(1L);

        assertEquals(1L, question.getId());
    }

    @Test
    void findAnswerByIdReturnsCorrectAnswer() {
        Answer answer = repository.findAnswerById(11L);

        assertEquals(11L, answer.getId());
    }

    @Test
    void findQuestionByIdThrowsExceptionWhenNotFound() {
        assertThrows(RuntimeException.class, () -> repository.findQuestionById(NON_EXISTENT_ID));
    }

    @Test
    void findAnswerByIdThrowsExceptionWhenNotFound() {
        assertThrows(RuntimeException.class, () -> repository.findAnswerById(NON_EXISTENT_ID));
    }
}