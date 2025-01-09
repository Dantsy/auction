package ru.otus.spring.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auction_questions")
@NamedEntityGraph(name = "auctionQuestion-answersChoices-entity-graph",
        attributeNodes = {@NamedAttributeNode("answersChoices")})
public class AuctionQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(targetEntity = AnswerChoice.class,
            mappedBy = "auctionQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<AnswerChoice> answersChoices;

    @Override
    public String toString() {
        return "AuctionQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answersChoices=" + answersChoices +
                '}';
    }
}
