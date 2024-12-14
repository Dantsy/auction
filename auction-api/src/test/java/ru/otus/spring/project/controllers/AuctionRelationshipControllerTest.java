package ru.otus.spring.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.project.dto.AuctionRelationshipDto;
import ru.otus.spring.project.services.AuctionRelationshipService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuctionRelationshipController.class)
public class AuctionRelationshipControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionRelationshipService auctionRelationshipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnCorrectAuctionRelationshipList() throws Exception {
        List<AuctionRelationshipDto> auctionRelationshipDtoList = getAuctionRelationshipDtoList();
        given(auctionRelationshipService.findAll()).willReturn(auctionRelationshipDtoList);

        mvc.perform(get("/api/auction-relationships"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(auctionRelationshipDtoList)));
    }

    private List<AuctionRelationshipDto> getAuctionRelationshipDtoList() {
        AuctionRelationshipDto relationship1 = new AuctionRelationshipDto();
        relationship1.setSourceName("bidder1");
        relationship1.setSourceUsername("bidder1");
        relationship1.setTargetName("bidder2");
        relationship1.setTargetUsername("bidder2");

        AuctionRelationshipDto relationship2 = new AuctionRelationshipDto();
        relationship2.setSourceName("bidder2");
        relationship2.setSourceUsername("bidder2");
        relationship2.setTargetName("bidder3");
        relationship2.setTargetUsername("bidder3");

        return List.of(relationship1, relationship2);
    }
}

