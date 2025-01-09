package ru.otus.spring.project.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.project.models.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @EntityGraph(value = "answer-question-gamer-entity-graph")
    List<Answer> findAllByBidderId(Long gamerId);

}