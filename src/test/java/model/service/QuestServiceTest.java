package model.service;

import model.entity.Answer;
import model.entity.Quest;
import model.entity.Question;
import model.entity.Result;
import model.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {
    private QuestService service;
    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(QuestionRepository.class);
        service = new QuestService(repository);
    }

    @Test
    void createQuestionReturnsQuestWithFirstQuestion() {
        Question firstQuestion = new Question();
        firstQuestion.setId(1L);

        Question secondQuestion = new Question();
        secondQuestion.setId(2L);

        Mockito.when(repository.findAllQuestions()).thenReturn(List.of(firstQuestion, secondQuestion));

        Quest quest = service.createNewQuest();

        assertNotNull(quest);
        assertFalse(quest.isOver());
        assertFalse(quest.isWin());
        assertEquals(firstQuestion, quest.getCurrentQuestion());
    }

    @Test
    void processAnswerLoseSetsQuestOver() {
        Answer loseAnswer = new Answer();
        loseAnswer.setId(15L);
        loseAnswer.setResult(Result.LOSE);

        Mockito.when(repository.findAnswerById(15L)).thenReturn(loseAnswer);

        Quest quest =  new Quest(List.of(new Question()));
        quest.start();

        service.processAnswer(quest, 15L);

        assertTrue(quest.isOver());
        assertFalse(quest.isWin());
    }

    @Test
    void processAnswerWinSetsQuestOver() {
        Answer winAnswer = new Answer();
        winAnswer.setId(15L);
        winAnswer.setResult(Result.WIN);

        Mockito.when(repository.findAnswerById(15L)).thenReturn(winAnswer);

        Quest quest =  new Quest(List.of(new Question()));
        quest.start();

        service.processAnswer(quest, 15L);

        assertTrue(quest.isOver());
        assertTrue(quest.isWin());
    }

    @Test
    void processAnswerNoResultSetsNewQuestion() {
        Question firstQuestion = new Question();
        firstQuestion.setId(1L);

        Question secondQuestion = new Question();
        secondQuestion.setId(2L);

        Answer noResultAnswer = new Answer();
        noResultAnswer.setId(11L);
        noResultAnswer.setQuestionId(1L);
        noResultAnswer.setNextQuestionId(2L);

        Mockito.when(repository.findAnswerById(11L)).thenReturn(noResultAnswer);
        Mockito.when(repository.findQuestionById(2L)).thenReturn(secondQuestion);

        Quest quest =  new Quest(List.of(firstQuestion, secondQuestion));
        quest.start();

        service.processAnswer(quest, 11L);

        assertEquals(secondQuestion, quest.getCurrentQuestion());
    }
}