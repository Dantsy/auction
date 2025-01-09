package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.models.AuctionQuestion;
import ru.otus.spring.project.repositories.AuctionQuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionQuestionServiceImpl implements AuctionQuestionService {

    private final AuctionQuestionRepository auctionQuestionRepository;

    @Override
    public List<AuctionQuestion> findAll() {
        return auctionQuestionRepository.findAll();
    }

    @Override
    public Optional<AuctionQuestion> findById(Long id) {
        return auctionQuestionRepository.findById(id);
    }
}