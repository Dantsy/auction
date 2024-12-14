package ru.otus.spring.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.project.dto.BidderDto;
import ru.otus.spring.project.services.BidderService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;

@WebMvcTest(BidderController.class)
public class BidderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BidderService bidderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnCorrectOneBidderByUsername() throws Exception {
        BidderDto bidder = getBidder();

        given(bidderService.findBidderByUsername(bidder.getUsername())).willReturn(bidder);

        mvc.perform(get("/api/bidders/{username}", "bidder")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(content().json(objectMapper.writeValueAsString(bidder)));
    }

    @Test
    public void shouldDeleteBidderByUsername() throws Exception {
        mvc.perform(post("/api/bidders/{username}", "bidder")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bidderService, times(1)).deleteBidderByUsername("bidder");
    }

    @Test
    void shouldReturnCorrectApprovedBiddersList() throws Exception {
        List<BidderDto> bidderDtoList = getBidderDtoList();

        given(bidderService.findAllApprovedBidders()).willReturn(bidderDtoList);

        mvc.perform(get("/api/bidders"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bidderDtoList)));
    }

    private BidderDto getBidder() {
        BidderDto bidderDto = new BidderDto();
        bidderDto.setId(1L);
        bidderDto.setUsername("bidder");
        return bidderDto;
    }

    private List<BidderDto> getBidderDtoList() {
        BidderDto bidderDto = new BidderDto();
        bidderDto.setId(1L);
        bidderDto.setUsername("bidder");

        BidderDto bidderDto2 = new BidderDto();
        bidderDto2.setId(2L);
        bidderDto2.setUsername("bidder2");

        return List.of(bidderDto, bidderDto2);
    }
}