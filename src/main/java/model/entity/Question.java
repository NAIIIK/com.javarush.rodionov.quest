package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Question implements Entity {
    private Long id;
    private String text;
    private final List<Answer> answers = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }
}
