package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AnswersCreationDto {

    private List<Answer> answers;

    public void addAnswer(Answer answer) {
        if (this.answers == null) {
            this.answers = new ArrayList<>();
        }
        this.answers.add(answer);
    }

    @Override
    public String toString() {
        return "AnswersCreationDto{" +
                "answers=" + answers +
                '}';
    }
}
