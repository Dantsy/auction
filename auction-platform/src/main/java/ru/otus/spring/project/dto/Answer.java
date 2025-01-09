package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Answer {

    private Question question;

    private String answer;

    @Override
    public String toString() {
        return "Answer{question=" + question +
                ", answer=" + answer + "}";
    }
}