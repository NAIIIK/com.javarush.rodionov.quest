package config;

import lombok.Getter;
import model.repository.FileQuestionRepository;
import model.repository.QuestionRepository;
import model.repository.mapper.JsonMapper;
import model.repository.mapper.Mapper;
import model.service.QuestService;
import util.Constants;

@Getter
public class AppContext {
    private final QuestService questService;

    public AppContext() {
        Mapper mapper = new JsonMapper();
        QuestionRepository questionRepository = new FileQuestionRepository(Constants.QUESTIONS_FILE_NAME, mapper);
        this.questService = new QuestService(questionRepository);
    }
}
