package ru.otus.spring.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bidders")
@NoArgsConstructor
@NamedEntityGraph(name = "bidder-auctionItem-entity-graph",
        attributeNodes = @NamedAttributeNode("auctionItem"))
public class Bidder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String present;

    @OneToMany(targetEntity = Answer.class,
            mappedBy = "bidder",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Answer> answers;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_item_id")
    private AuctionItem auctionItem;

    @Override
    public String toString() {
        return "Bidder{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", present='" + present + '\'' +
                '}';
    }
}
