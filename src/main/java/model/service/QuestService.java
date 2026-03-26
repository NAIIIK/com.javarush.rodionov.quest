package model.service;

import model.entity.Answer;
import model.entity.Quest;
import model.entity.Question;
import model.entity.Result;
import model.repository.QuestionRepository;

public class QuestService {
    private final QuestionRepository repository;

    public QuestService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Quest createNewQuest() {
        Quest quest = new Quest(repository.findAllQuestions());
        quest.start();
        return quest;
    }

    public String processAnswer(Quest quest, Long answerId) {
        Answer answer = repository.findAnswerById(answerId);

        if (answer.getResult() == Result.LOSE) {
            quest.setOver(true);
            return answer.getLoseText();
        } else if (answer.getResult() == Result.WIN) {
            quest.setWin(true);
            quest.setOver(true);
        } else {
            Question newCurrentQuestion = repository.findQuestionById(answer.getNextQuestionId());
            quest.setCurrentQuestion(newCurrentQuestion);
        }

        return null;
    }
}
