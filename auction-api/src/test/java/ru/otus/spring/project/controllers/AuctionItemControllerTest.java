package ru.otus.spring.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.project.dto.AuctionItemDto;
import ru.otus.spring.project.dto.BidderDto;
import ru.otus.spring.project.services.AuctionItemService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuctionItemController.class)
public class AuctionItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionItemService auctionItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAuctionItemByUsernameTest() throws Exception {
        given(auctionItemService.findAuctionItemByUsername("username")).willReturn(getAuctionItem());

        mvc.perform(get("/api/auction-items/username"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(getAuctionItem())));
    }

    private AuctionItemDto getAuctionItem() {
        AuctionItemDto auctionItem = new AuctionItemDto();
        auctionItem.setId(1L);
        auctionItem.setAuctionItemText("Auction Item");
        BidderDto bidder = new BidderDto();
        bidder.setId(1L);
        bidder.setUsername("username");
        auctionItem.setBidder(bidder);
        return auctionItem;
    }
}
