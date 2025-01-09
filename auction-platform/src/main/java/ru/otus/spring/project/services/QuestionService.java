package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.dto.Question;
import ru.otus.spring.project.feign.AuctionProxi;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@ConditionalOnProperty(name = "use", havingValue = "feign")
@RequiredArgsConstructor
public class QuestionService {

    private final AuctionProxi auctionProxi;

    public List<Question> getAllQuestions() {
        List<Question> questions = auctionProxi.getAllQuestions();
        questions.sort(Comparator.comparing(Question::getId));

        return questions;
    }

    public Question getQuestionById(Long id) {
        return auctionProxi.getQuestionById(id);
    }
}