package ru.otus.spring.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answers")
@NamedEntityGraph(name = "answer-auctionQuestion-bidder-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "auctionQuestion", subgraph = "auctionQuestion-answersChoices-entity-graph"),
                @NamedAttributeNode("bidder")
        }, subgraphs = {
        @NamedSubgraph(name = "auctionQuestion-answersChoices-entity-graph",
                attributeNodes = @NamedAttributeNode("answersChoices"))
})
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;

    @ManyToOne
    @JoinColumn(name = "auction_question_id")
    private AuctionQuestion auctionQuestion;

    private String answer;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", auctionQuestion=" + auctionQuestion +
                ", answer='" + answer + '\'' +
                '}';
    }
}
