package ru.otus.spring.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "answers_choices")
@NoArgsConstructor
public class AnswerChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String choice;

    @ManyToOne
    @JoinColumn(name = "auction_question_id")
    private AuctionQuestion auctionQuestion;

    public AnswerChoice(Long id, String choice) {
        this.id = id;
        this.choice = choice;
    }
}