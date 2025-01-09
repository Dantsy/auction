package ru.otus.spring.project.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.project.dto.AnswerDto;
import ru.otus.spring.project.dto.AuctionQuestionDto;
import ru.otus.spring.project.models.Answer;
import ru.otus.spring.project.models.AuctionQuestion;

@Component
@RequiredArgsConstructor
public class AnswerConverter {

    private final AuctionQuestionConverter auctionQuestionConverter;

    public AnswerDto fromDomainObject(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        answerDto.setAnswer(answer.getAnswer());

        AuctionQuestion auctionQuestion = answer.getAuctionQuestion();
        AuctionQuestionDto auctionQuestionDto = auctionQuestionConverter.fromDomainObject(auctionQuestion);

        answerDto.setAuctionQuestion(auctionQuestionDto);
        return answerDto;
    }

    public Answer toDomainObject(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setAnswer(answerDto.getAnswer());

        AuctionQuestionDto auctionQuestionDto = answerDto.getAuctionQuestion();
        AuctionQuestion auctionQuestion = auctionQuestionConverter.toDomainObject(auctionQuestionDto);

        answer.setAuctionQuestion(auctionQuestion);
        return answer;
    }
}