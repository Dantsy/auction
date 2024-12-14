package ru.otus.spring.project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.spring.project.dto.Answer;
import ru.otus.spring.project.dto.Bidder;
import ru.otus.spring.project.dto.AuctionItem;
import ru.otus.spring.project.dto.Question;
import ru.otus.spring.project.services.BidderService;
import ru.otus.spring.project.services.JwtService;
import ru.otus.spring.project.services.AuctionItemService;
import ru.otus.spring.project.services.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionFormController {

    private final QuestionService questionService;

    private final BidderService bidderService;

    private final JwtService jwtService;

    private final AuctionItemService auctionItemService;

    @GetMapping("/form")
    public String formPage(HttpServletRequest request, Model model) {
        String username;

        try {
            username = jwtService.getUsernameByRequest(request);
        } catch (NotFoundException e) {
            return "redirect:/auction/login";
        }

        AuctionItem auctionItem = auctionItemService.findAuctionItemByUsername(username);
        if (auctionItem != null) {
            return "redirect:/auction";
        }

        Bidder bidder = bidderService.findBidderByUsername(username);
        if (bidder != null) {
            return "redirect:/auction/auctionItem";
        }

        bidder = new Bidder();
        setAnswers(bidder);
        model.addAttribute("bidder", bidder);
        return "form";
    }

    @PostMapping("/form")
    public String createAuctionItems(@ModelAttribute("bidder") @Valid Bidder bidder,
                                     BindingResult bindingResult, HttpServletRequest request) {
        String username = jwtService.getUsernameByRequest(request);
        if (bindingResult.hasErrors()) {
            setAnswers(bidder);
            return "form";
        }

        List<Answer> answers = bidder.getAnswers();
        List<Answer> fullAnswers = new ArrayList<>();

        for (Answer answer : answers) {
            Question question = questionService.getQuestionById(answer.getQuestion().getId());
            Answer fullAnswer = new Answer();
            fullAnswer.setAnswer(answer.getAnswer());
            fullAnswer.setQuestion(question);
            fullAnswers.add(fullAnswer);
        }

        bidder.setAnswers(fullAnswers);
        bidder.setUsername(username);
        bidderService.saveBidder(bidder);
        return "redirect:/auction/auctionItem";
    }

    private void setAnswers(Bidder bidder) {
        List<Question> questions = questionService.getAllQuestions();
        List<Answer> answers = new ArrayList<>();

        for (Question question : questions) {
            Answer answer = new Answer();
            answer.setQuestion(question);
            answers.add(answer);
        }

        bidder.setAnswers(answers);
    }
}