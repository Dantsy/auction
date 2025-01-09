package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.dto.Answer;
import ru.otus.spring.project.dto.Bidder;
import ru.otus.spring.project.dto.AuctionItem;
import ru.otus.spring.project.feign.AuctionProxi;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionItemService {

    private final AuctionProxi auctionProxi;

    public String makeAuctionItem(Bidder bidder) {
        List<Answer> answers = bidder.getAnswers();
        String ageAnswer = "";
        String newSkillAnswer = "";
        String demeanorAnswer = "";

        for (Answer answer : answers) {
            if (answer.getQuestion().getQuestion().contains("years")) {
                ageAnswer = answer.getAnswer();
            }
            if (answer.getQuestion().getQuestion().contains("learned")) {
                newSkillAnswer = answer.getAnswer();
            }
            if (answer.getQuestion().getQuestion().contains("led")) {
                demeanorAnswer = answer.getAnswer();
            }
        }

        return "Dear auction organizer!!!<br/>" +
                "My name is " + bidder.getName() + ", мне " + ageAnswer + ". " +
                "I learned" + newSkillAnswer + " and behaved " + demeanorAnswer + ".<br/>" +
                "I dream of winning at an auction " + bidder.getAuctionItemName() + ".<br/>" +
                "I hope that my bid will be the highest. " +
                "Sincerely, " + bidder.getName() + ".";
    }

    public AuctionItem saveAuctionItem(AuctionItem auctionItem) {
        return auctionProxi.saveAuctionItem(auctionItem);
    }

    public AuctionItem findAuctionItemByUsername(String username) {
        return auctionProxi.findAuctionItemByUsername(username);
    }

    public AuctionItem findTargetAuctionItemByUsername(String username) {
        return auctionProxi.findTargetAuctionItemByUsername(username);
    }
}
