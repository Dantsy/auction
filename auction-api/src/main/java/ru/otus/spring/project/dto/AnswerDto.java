package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDto {

    private Long id;

    private BidderDto bidder;

    private AuctionQuestionDto auctionQuestion;

    private String answer;

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", auctionQuestion=" + auctionQuestion +
                ", answer='" + answer + '\'' +
                '}';
    }
}
