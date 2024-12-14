package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.dto.Bidder;
import ru.otus.spring.project.feign.AuctionProxi;

import java.util.List;

@Service
@Slf4j
@ConditionalOnProperty(name = "use", havingValue = "feign")
@RequiredArgsConstructor
public class BidderService {

    private final AuctionProxi auctionProxi;

    public Bidder saveBidder(Bidder bidder) {
        return auctionProxi.saveBidder(bidder);
    }

    public Bidder findBidderByUsername(String username) {
        return auctionProxi.findBidderByUsername(username);
    }

    public void deleteBidderByUsername(String username) {
        auctionProxi.deleteBidderByUsername(username);
    }

    public List<Bidder> findAllApprovedBidders() {
        return auctionProxi.findAllApprovedBidders();
    }
}