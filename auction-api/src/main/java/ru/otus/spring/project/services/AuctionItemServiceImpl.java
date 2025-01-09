package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.converter.BidderConverter;
import ru.otus.spring.project.converter.AuctionItemConverter;
import ru.otus.spring.project.dto.BidderDto;
import ru.otus.spring.project.dto.AuctionItemDto;
import ru.otus.spring.project.models.Bidder;
import ru.otus.spring.project.models.AuctionItem;
import ru.otus.spring.project.models.AuctionRelationship;
import ru.otus.spring.project.repositories.BidderRepository;
import ru.otus.spring.project.repositories.AuctionItemRepository;
import ru.otus.spring.project.repositories.AuctionRelationshipRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionItemServiceImpl implements AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;

    private final AuctionItemConverter auctionItemConverter;

    private final BidderRepository bidderRepository;

    private final BidderConverter bidderConverter;

    private final AuctionRelationshipRepository auctionRelationshipRepository;

    @Override
    public AuctionItemDto insert(AuctionItemDto auctionItemDto) {
        AuctionItem auctionItem = new AuctionItem();
        auctionItem.setAuctionItemText(auctionItemDto.getAuctionItemText());
        AuctionItem savedAuctionItem = auctionItemRepository.save(auctionItem);

        String username = auctionItemDto.getBidder().getUsername();
        Bidder bidder = bidderRepository.findBidderByUsername(username);
        bidder.setAuctionItem(savedAuctionItem);
        bidderRepository.save(bidder);

        return auctionItemConverter.fromDomainObject(savedAuctionItem);
    }

    @Override
    public AuctionItemDto findAuctionItemByUsername(String username) {
        AuctionItem auctionItem = auctionItemRepository.findAuctionItemByUsername(username);
        if (auctionItem == null) {
            return null;
        }
        Bidder bidder = bidderRepository.findBidderByUsername(username);
        AuctionItemDto auctionItemDto = auctionItemConverter.fromDomainObject(auctionItem);
        BidderDto bidderDto = bidderConverter.fromDomainObjectWithoutAnswers(bidder);
        auctionItemDto.setBidder(bidderDto);
        return auctionItemDto;
    }

    @Override
    public AuctionItem findTargetAuctionItemByUsername(String username) {
        List<AuctionRelationship> relationships = auctionRelationshipRepository.findAll();
        if (relationships.size() == 0) {
            return null;
        }

        Optional<AuctionRelationship> relationship = relationships.stream()
                .filter(g -> g.getSourceBidder().getUsername().equals(username))
                .findFirst();

        if (relationship.isEmpty()) {
            return null;
        }

        String targetUsername = relationship.get().getTargetBidder().getUsername();
        return auctionItemRepository.findAuctionItemByUsername(targetUsername);
    }
}