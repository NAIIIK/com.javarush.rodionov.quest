package model.repository;

import model.entity.Answer;
import model.entity.Question;
import model.repository.mapper.Mapper;

import java.util.List;

public class FileQuestionRepository implements QuestionRepository {
    private final List<Question> questions;

    public FileQuestionRepository(String fileName, Mapper mapper) {
        this.questions = mapper.mapQuestions(fileName);
    }

    @Override
    public Question findQuestionById(Long id) {
        return questions.stream()
                .filter(q -> q.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Question with id " + id + " not found!"));
    }

    @Override
    public Answer findAnswerById(Long answerId) {
        return questions.stream()
                .flatMap(q -> q.getAnswers().stream())
                .filter(a -> a.getId().equals(answerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Answer with id " + answerId + " not found!"));
    }

    @Override
    public List<Question> findAllQuestions() {
        return questions;
    }
}