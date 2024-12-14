package ru.otus.spring.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.project.dto.AuctionRelationshipDto;
import ru.otus.spring.project.services.AuctionRelationshipService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuctionRelationshipController {

    private final AuctionRelationshipService auctionRelationshipService;

    @GetMapping("/api/auction-relationships")
    public List<AuctionRelationshipDto> getAuctionRelationships() {
        return auctionRelationshipService.findAll();
    }

    @PostMapping("/api/auction-relationships/mix")
    public void distributeAuctionItems() {
        auctionRelationshipService.distributeAuctionItems();
    }
}