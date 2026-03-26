package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Answer {
    private Long id;
    private String text;
    private Long questionId;
    private Long nextQuestionId;
    private Result result;
    private String loseText;
}
