package ru.otus.spring.project.services;

import ru.otus.spring.project.models.AuctionQuestion;

import java.util.List;
import java.util.Optional;

public interface AuctionQuestionService {

    List<AuctionQuestion> findAll();

    Optional<AuctionQuestion> findById(Long id);
}