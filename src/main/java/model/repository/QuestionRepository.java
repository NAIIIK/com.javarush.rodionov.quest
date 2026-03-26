package model.repository;

import model.entity.Answer;
import model.entity.Question;

import java.util.List;

public interface QuestionRepository {
    Question findQuestionById(Long id);
    Answer findAnswerById(Long id);
    List<Question> findAllQuestions();
}
