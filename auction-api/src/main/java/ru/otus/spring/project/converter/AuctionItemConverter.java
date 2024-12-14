package ru.otus.spring.project.converter;

import org.springframework.stereotype.Component;
import ru.otus.spring.project.dto.AuctionItemDto;
import ru.otus.spring.project.models.AuctionItem;

@Component
public class AuctionItemConverter {

    public AuctionItemDto fromDomainObject(AuctionItem auctionItem) {
        AuctionItemDto auctionItemDto = new AuctionItemDto();
        auctionItemDto.setAuctionItemText(auctionItem.getAuctionItemText());
        return auctionItemDto;
    }
}
