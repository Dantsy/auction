package ru.otus.spring.project.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.project.dto.Bidder;
import ru.otus.spring.project.dto.AuctionItem;
import ru.otus.spring.project.dto.Question;
import ru.otus.spring.project.dto.AuctionRelationship;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "auction-api")
public interface AuctionProxi {

    @CircuitBreaker(name = "auction-api", fallbackMethod = "fallbackGetAllQuestions")
    @GetMapping(value = "/api/questions")
    List<Question> getAllQuestions();

    @CircuitBreaker(name = "auction-api", fallbackMethod = "fallbackGetQuestionById")
    @GetMapping(value = "/api/questions/{id}")
    Question getQuestionById(@PathVariable("id") Long id);

    @CircuitBreaker(name = "auction-api")
    @GetMapping(value = "/api/bidders")
    Bidder saveBidder(Bidder bidder);

    @CircuitBreaker(name = "auction-api")
    @GetMapping(value = "/api/bidders/{username}")
    Bidder findBidderByUsername(@PathVariable("username") String username);

    @CircuitBreaker(name = "auction-api")
    @GetMapping(value = "/api/auction-items/{username}")
    AuctionItem findAuctionItemByUsername(@PathVariable("username") String username);

    @CircuitBreaker(name = "auction-api")
    @GetMapping(value = "/api/target-auction-item/{username}")
    AuctionItem findTargetAuctionItemByUsername(@PathVariable("username") String username);

    @CircuitBreaker(name = "auction-api")
    @PostMapping(value = "/api/auction-items")
    AuctionItem saveAuctionItem(AuctionItem auctionItem);

    @CircuitBreaker(name = "auction-api")
    @PostMapping(value = "/api/bidders/{username}")
    void deleteBidderByUsername(@PathVariable("username") String username);

    @CircuitBreaker(name = "auction-api")
    @GetMapping(value = "/api/bidders")
    List<Bidder> findAllApprovedBidders();

    @CircuitBreaker(name = "auction-api")
    @GetMapping(value = "/api/auction-relationships")
    List<AuctionRelationship> findAllAuctionRelationships();

    @CircuitBreaker(name = "auction-api")
    @PostMapping(value = "/api/auction-relationships/mix")
    void distributeAuctionItems();

    default List<Question> fallbackGetAllQuestions(Throwable throwable) {
        return Collections.emptyList();
    }

    default Question fallbackGetQuestionById(Throwable throwable) {
        return new Question();
    }
}