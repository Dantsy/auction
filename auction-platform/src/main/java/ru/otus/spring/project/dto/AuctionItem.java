package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuctionItem {

    private String auctionItemText;

    private Bidder bidder;

    @Override
    public String toString() {
        return "AuctionItem{" +
                "auctionItemText='" + auctionItemText + '\'' +
                ", bidder=" + bidder +
                '}';
    }
}