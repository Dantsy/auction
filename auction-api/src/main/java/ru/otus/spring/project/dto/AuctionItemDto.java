package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuctionItemDto {
    private Long id;

    private String auctionItemText;

    private BidderDto bidder;
}