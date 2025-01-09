package ru.otus.spring.project.services;

import ru.otus.spring.project.dto.AuctionItemDto;
import ru.otus.spring.project.models.AuctionItem;

public interface AuctionItemService {

    AuctionItemDto insert(AuctionItemDto auctionItemDto);

    AuctionItemDto findAuctionItemByUsername(String username);

    AuctionItem findTargetAuctionItemByUsername(String username);
}