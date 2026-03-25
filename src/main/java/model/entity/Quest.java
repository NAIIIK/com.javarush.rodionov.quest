package model.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Quest {
    private List<Question> questions;
    private boolean isOver;
    private boolean isWin;
    private Question currentQuestion;

    public Quest(@NonNull List<Question> questions) {
        this.questions = questions;
    }

    public void start() {
        isOver = false;
        isWin = false;
        currentQuestion = questions.get(0);
    }
}
