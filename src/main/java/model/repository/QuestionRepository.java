package model.repository;

import model.entity.Answer;
import model.entity.Question;

import java.util.List;

public class QuestionRepository {
    private final List<Question> questions;

    public QuestionRepository(String fileName) {
        this.questions = new JsonMapper().mapQuestions(fileName);
    }

    public Question findQuestionById(Long id) {
        return questions.stream()
                .filter(q -> q.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Question with id " + id + " not found!"));
    }

    public Answer findAnswerById(Long answerId) {
        return questions.stream()
                .flatMap(q -> q.getAnswers().stream())
                .filter(a -> a.getId().equals(answerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Answer with id " + answerId + " not found!"));
    }

    public List<Question> findAllQuestions() {
        return questions;
    }
}