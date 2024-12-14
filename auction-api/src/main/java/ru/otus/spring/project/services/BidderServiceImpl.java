package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.converter.AnswerConverter;
import ru.otus.spring.project.converter.BidderConverter;
import ru.otus.spring.project.dto.AnswerDto;
import ru.otus.spring.project.dto.BidderDto;
import ru.otus.spring.project.models.Answer;
import ru.otus.spring.project.models.Bidder;
import ru.otus.spring.project.repositories.AnswerRepository;
import ru.otus.spring.project.repositories.BidderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BidderServiceImpl implements BidderService {

    private final BidderRepository bidderRepository;

    private final BidderConverter bidderConverter;

    private final AnswerConverter answerConverter;

    private final AnswerRepository answerRepository;

    @Override
    public BidderDto insert(BidderDto bidderDto) {
        Bidder bidder = new Bidder();
        bidder.setName(bidderDto.getName());
        bidder.setPresent(bidderDto.getPresent());
        bidder.setUsername(bidderDto.getUsername());

        Bidder savedBidder = bidderRepository.save(bidder);

        List<Answer> answers = bidderDto
                .getAnswers()
                .stream()
                .map(answerConverter::toDomainObject)
                .toList();

        for (Answer answer : answers) {
            answer.setBidder(savedBidder);
        }

        List<Answer> savedAnswer = answerRepository.saveAll(answers);
        log.info("savedAnswer: " + savedAnswer);
        return bidderConverter.fromDomainObject(savedBidder);
    }

    @Override
    public BidderDto findBidderByUsername(String username) {
        Bidder bidder = bidderRepository.findBidderByUsername(username);
        if (bidder == null) {
            return null;
        }
        BidderDto bidderDto = bidderConverter.fromDomainObjectWithoutAnswers(bidder);
        List<Answer> answers = answerRepository.findAllByBidderId(bidder.getId());
        List<AnswerDto> answerDtoList = answers.stream().map(answerConverter::fromDomainObject).toList();
        bidderDto.setAnswers(answerDtoList);
        bidder.setAnswers(answers);
        return bidderDto;
    }

    @Override
    public void deleteBidderByUsername(String username) {
        Bidder bidder = bidderRepository.findBidderByUsername(username);
        bidderRepository.delete(bidder);
    }

    @Override
    public List<BidderDto> findAllApprovedBidders() {
        return bidderRepository.findAll().stream()
                .filter(b -> b.getAuctionItem() != null)
                .map(bidderConverter::fromDomainObjectWithoutAnswers)
                .toList();
    }
}