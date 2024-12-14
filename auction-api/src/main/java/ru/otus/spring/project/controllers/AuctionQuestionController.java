package ru.otus.spring.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.project.converter.AuctionQuestionConverter;
import ru.otus.spring.project.dto.AuctionQuestionDto;
import ru.otus.spring.project.exeptions.NotFoundException;
import ru.otus.spring.project.services.AuctionQuestionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuctionQuestionController {

    private final AuctionQuestionService auctionQuestionService;

    private final AuctionQuestionConverter auctionQuestionConverter;

    @GetMapping("/api/auction-questions")
    public List<AuctionQuestionDto> getAllAuctionQuestions() {
        return auctionQuestionService
                .findAll()
                .stream()
                .map(auctionQuestionConverter::fromDomainObject)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/auction-questions/{id}")
    public AuctionQuestionDto getAuctionQuestionById(@PathVariable("id") long id) {
        return auctionQuestionConverter.fromDomainObject(auctionQuestionService.findById(id)
                .orElseThrow(() -> new NotFoundException("Auction Question not found")));
    }
}