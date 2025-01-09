package ru.otus.spring.project.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.project.models.Bidder;

import java.util.List;

public interface BidderRepository extends JpaRepository<Bidder, Long> {

     Bidder findBidderByUsername(String username);

     @EntityGraph(value = "bidder-auctionItem-entity-graph")
     List<Bidder> findAll();
}