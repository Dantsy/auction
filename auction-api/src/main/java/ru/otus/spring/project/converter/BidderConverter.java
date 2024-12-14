package ru.otus.spring.project.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.project.dto.AnswerDto;
import ru.otus.spring.project.dto.BidderDto;
import ru.otus.spring.project.models.Answer;
import ru.otus.spring.project.models.Bidder;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BidderConverter {

    private final AnswerConverter answerConverter;

    public BidderDto fromDomainObject(Bidder bidder) {
        BidderDto bidderDto = new BidderDto();
        bidderDto.setId(bidder.getId());
        bidderDto.setUsername(bidder.getUsername());
        bidderDto.setName(bidder.getName());
        bidderDto.setPresent(bidder.getPresent());

        List<AnswerDto> answerDtoList = new ArrayList<>();

        if (bidder.getAnswers() != null) {
            for (Answer answer : bidder.getAnswers()) {
                answerDtoList.add(answerConverter.fromDomainObject(answer));
            }
        }

        bidderDto.setAnswers(answerDtoList);
        return bidderDto;
    }

    public BidderDto fromDomainObjectWithoutAnswers(Bidder bidder) {
        BidderDto bidderDto = new BidderDto();
        bidderDto.setId(bidder.getId());
        bidderDto.setUsername(bidder.getUsername());
        bidderDto.setName(bidder.getName());
        bidderDto.setPresent(bidder.getPresent());
        return bidderDto;
    }

    public Bidder toDomainObject(BidderDto bidderDto) {
        Bidder bidder = new Bidder();
        bidder.setId(bidderDto.getId());
        bidder.setUsername(bidderDto.getUsername());
        bidder.setName(bidderDto.getName());
        bidder.setPresent(bidderDto.getPresent());
        List<Answer> answerList = new ArrayList<>();

        for (AnswerDto answerDto : bidderDto.getAnswers()) {
            answerList.add(answerConverter.toDomainObject(answerDto));
        }
        bidder.setAnswers(answerList);
        return bidder;
    }
}