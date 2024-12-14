package ru.otus.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerChoiceDto {
    private Long id;

    private String choice;
}