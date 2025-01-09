package ru.otus.spring.project.services;

import ru.otus.spring.project.dto.AuctionRelationshipDto;

import java.util.List;

public interface AuctionRelationshipService {

    List<AuctionRelationshipDto> findAll();

    void distributeAuctionItems();
}