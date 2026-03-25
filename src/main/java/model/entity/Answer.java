package model.entity;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class Answer implements Entity {
    private Long id;
    private String text;
    private Long questionId;
    private Long nextQuestionId;
    private Result result;
    private String loseText;

    @Override
    public Long getId() {
        return id;
    }
}
