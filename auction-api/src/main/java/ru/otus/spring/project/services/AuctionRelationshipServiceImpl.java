package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.converter.AuctionRelationshipConverter;
import ru.otus.spring.project.dto.AuctionRelationshipDto;
import ru.otus.spring.project.models.Bidder;
import ru.otus.spring.project.models.AuctionRelationship;
import ru.otus.spring.project.repositories.BidderRepository;
import ru.otus.spring.project.repositories.AuctionRelationshipRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionRelationshipServiceImpl implements AuctionRelationshipService {

    private final AuctionRelationshipRepository auctionRelationshipRepository;

    private final AuctionRelationshipConverter auctionRelationshipConverter;

    private final BidderRepository bidderRepository;

    @Override
    public List<AuctionRelationshipDto> findAll() {
        List<AuctionRelationship> relationships = auctionRelationshipRepository.findAll();

        if (relationships == null) {
            return null;
        }

        return relationships
                .stream().map(auctionRelationshipConverter::fromDomainObject).toList();
    }

    @Override
    public void distributeAuctionItems() {
        List<Bidder> bidders = bidderRepository.findAll().stream().filter(b -> b.getAuctionItem() != null)
                .sorted(Comparator.comparingLong(Bidder::getId)).toList();
        List<AuctionRelationship> relationships = new ArrayList<>();
        int biddersSize = bidders.size();
        for (int i = 0; i < biddersSize; i++) {
            AuctionRelationship relationship = new AuctionRelationship();
            relationship.setSourceBidder(bidders.get(i));
            if (i == biddersSize - 2) {
                relationship.setTargetBidder(bidders.get(0));
                relationships.add(relationship);
                continue;
            }
            if (i == biddersSize - 1) {
                relationship.setTargetBidder(bidders.get(1));
                relationships.add(relationship);
                continue;
            }
            relationship.setTargetBidder(bidders.get(i + 2));
            relationships.add(relationship);
        }
        auctionRelationshipRepository.saveAll(relationships);
    }
}
