package ru.otus.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionQuestionDto {
    private Long id;

    private String question;

    private List<AnswerChoiceDto> answersChoices;

    @Override
    public String toString() {
        return "AuctionQuestionDto{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
