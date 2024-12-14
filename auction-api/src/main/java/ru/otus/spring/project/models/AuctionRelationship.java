package ru.otus.spring.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auction_relationships")
@NoArgsConstructor
@NamedEntityGraph(name = "sourceBidder-targetBidder-entity-graph",
        attributeNodes = {@NamedAttributeNode("sourceBidder"), @NamedAttributeNode("targetBidder")})
public class AuctionRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "source_bidder_id")
    private Bidder sourceBidder;

    @OneToOne
    @JoinColumn(name = "target_bidder_id")
    private Bidder targetBidder;
}