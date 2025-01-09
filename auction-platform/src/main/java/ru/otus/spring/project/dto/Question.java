package ru.otus.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;

    private String question;

    private List<AnswerChoice> answersChoices;

    @Override
    public String toString() {
        return id + "," + question;
    }
}