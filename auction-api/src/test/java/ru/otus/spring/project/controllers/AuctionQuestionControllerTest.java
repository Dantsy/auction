package ru.otus.spring.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.project.converter.AuctionQuestionConverter;
import ru.otus.spring.project.dto.AuctionQuestionDto;
import ru.otus.spring.project.models.AuctionQuestion;
import ru.otus.spring.project.services.AuctionQuestionService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuctionQuestionController.class)
public class AuctionQuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionQuestionService auctionQuestionService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuctionQuestionConverter auctionQuestionConverter;

    @Test
    void shouldReturnCorrectAuctionQuestionList() throws Exception {
        List<AuctionQuestion> auctionQuestions = getAuctionQuestions();
        given(auctionQuestionService.findAll()).willReturn(auctionQuestions);

        List<AuctionQuestionDto> expectedResult = auctionQuestions.stream()
                .map(auctionQuestionConverter::fromDomainObject)
                .toList();

        mvc.perform(get("/api/auction-questions"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectAuctionQuestionById() throws Exception {
        AuctionQuestion auctionQuestion1 = new AuctionQuestion();
        auctionQuestion1.setId(1L);
        auctionQuestion1.setQuestion("question1");

        AuctionQuestionDto auctionQuestionDto = new AuctionQuestionDto();
        auctionQuestionDto.setId(1L);
        auctionQuestionDto.setQuestion("question1");

        given(auctionQuestionService.findById(auctionQuestion1.getId())).willReturn(Optional.of(auctionQuestion1));
        given(auctionQuestionConverter.fromDomainObject(auctionQuestion1)).willReturn(auctionQuestionDto);

        mvc.perform(get("/api/auction-questions/{id}", 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(content().json(objectMapper.writeValueAsString(auctionQuestionDto)));
    }

    private List<AuctionQuestion> getAuctionQuestions() {
        AuctionQuestion auctionQuestion1 = new AuctionQuestion();
        auctionQuestion1.setId(1L);
        auctionQuestion1.setQuestion("question1");

        AuctionQuestion auctionQuestion2 = new AuctionQuestion();
        auctionQuestion2.setId(2L);
        auctionQuestion2.setQuestion("question2");
        return List.of(auctionQuestion1, auctionQuestion2);
    }
}