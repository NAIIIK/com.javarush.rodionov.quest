package model.repository.mapper;

import model.entity.Question;

import java.util.List;

public interface Mapper {
    List<Question> mapQuestions(String fileName);
}
