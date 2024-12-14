package ru.otus.spring.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.project.dto.AuctionItemDto;
import ru.otus.spring.project.models.AuctionItem;
import ru.otus.spring.project.services.AuctionItemService;

@RestController
@RequiredArgsConstructor
public class AuctionItemController {

    private final AuctionItemService auctionItemService;

    @PostMapping("/api/auction-items")
    public AuctionItemDto saveAuctionItem(@RequestBody AuctionItemDto auctionItemDto) {
        return auctionItemService.insert(auctionItemDto);
    }

    @GetMapping("/api/auction-items/{username}")
    public AuctionItemDto findAuctionItemByUsername(@PathVariable("username") String username) {
        return auctionItemService.findAuctionItemByUsername(username);
    }

    @GetMapping("/api/target-auction-item/{username}")
    public AuctionItem findTargetAuctionItemByUsername(@PathVariable("username") String username) {
        return auctionItemService.findTargetAuctionItemByUsername(username);
    }
}
