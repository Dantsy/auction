package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.dto.AuctionRelationship;
import ru.otus.spring.project.feign.AuctionProxi;

import java.util.List;

@Service
@Slf4j
@ConditionalOnProperty(name = "use", havingValue = "feign")
@RequiredArgsConstructor
public class AuctionRelationshipService {

    private final AuctionProxi auctionProxi;

    public List<AuctionRelationship> findAllAuctionRelationships() {
        return auctionProxi.findAllAuctionRelationships();
    }

    public void distributeAuctionItems() {
        auctionProxi.distributeAuctionItems();
    }
}
