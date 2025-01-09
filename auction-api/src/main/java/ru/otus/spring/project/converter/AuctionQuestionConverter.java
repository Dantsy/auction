package ru.otus.spring.project.converter;

import org.springframework.stereotype.Component;
import ru.otus.spring.project.dto.AnswerChoiceDto;
import ru.otus.spring.project.dto.AuctionQuestionDto;
import ru.otus.spring.project.models.AnswerChoice;
import ru.otus.spring.project.models.AuctionQuestion;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuctionQuestionConverter {
    public AuctionQuestionDto fromDomainObject(AuctionQuestion auctionQuestion) {
        List<AnswerChoice> answerChoiceList = auctionQuestion.getAnswersChoices();

        List<AnswerChoiceDto> answerChoiceDtoList = new ArrayList<>();

        if (answerChoiceList != null) {
            answerChoiceDtoList = answerChoiceList.stream()
                    .map(x -> new AnswerChoiceDto(x.getId(), x.getChoice()))
                    .toList();
        }

        return new AuctionQuestionDto(auctionQuestion.getId(), auctionQuestion.getQuestion(), answerChoiceDtoList);
    }

    public AuctionQuestion toDomainObject(AuctionQuestionDto auctionQuestionDto) {
        List<AnswerChoiceDto> answerChoiceDtoList = auctionQuestionDto.getAnswersChoices();

        List<AnswerChoice> answerChoiceList = new ArrayList<>();

        if (answerChoiceDtoList != null) {
            answerChoiceList = answerChoiceDtoList.stream()
                    .map(x -> new AnswerChoice(x.getId(), x.getChoice()))
                    .toList();
        }

        return new AuctionQuestion(auctionQuestionDto.getId(), auctionQuestionDto.getQuestion(), answerChoiceList);
    }
}
