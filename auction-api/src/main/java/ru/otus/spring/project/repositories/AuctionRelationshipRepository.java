package ru.otus.spring.project.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.project.models.AuctionRelationship;

import java.util.List;

public interface AuctionRelationshipRepository extends JpaRepository<AuctionRelationship, Long> {

    @EntityGraph(value = "sourceBidder-targetBidder-entity-graph")
    List<AuctionRelationship> findAll();
}