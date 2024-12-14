package ru.otus.spring.project.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.project.models.AuctionQuestion;

import java.util.List;
import java.util.Optional;

public interface AuctionQuestionRepository extends JpaRepository<AuctionQuestion, Long> {

    @EntityGraph(value = "auctionQuestion-answersChoices-entity-graph")
    List<AuctionQuestion> findAll();

    @EntityGraph(value = "auctionQuestion-answersChoices-entity-graph")
    Optional<AuctionQuestion> findById(Long id);
}
