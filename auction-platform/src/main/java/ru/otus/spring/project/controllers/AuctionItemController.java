package ru.otus.spring.project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.spring.project.dto.Bidder;
import ru.otus.spring.project.dto.AuctionItem;
import ru.otus.spring.project.services.BidderService;
import ru.otus.spring.project.services.JwtService;
import ru.otus.spring.project.services.AuctionItemService;

@Controller
@Slf4j
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionItemController {

    private final JwtService jwtService;

    private final BidderService bidderService;

    private final AuctionItemService auctionItemService;

    @GetMapping("/auctionItem")
    public String auctionItemPage(HttpServletRequest request, Model model) {
        String username = "";
        boolean isSavedAuctionItem = false;

        try {
            username = jwtService.getUsernameByRequest(request);
        } catch (NotFoundException e) {
            return "redirect:/auction/login";
        }

        AuctionItem auctionItem = auctionItemService.findAuctionItemByUsername(username);

        if (auctionItem != null) {
            isSavedAuctionItem = true;
        } else {
            auctionItem = new AuctionItem();
            Bidder bidder = bidderService.findBidderByUsername(username);
            auctionItem.setAuctionItemText(auctionItemService.makeAuctionItem(bidder));
            auctionItem.setBidder(bidder);
        }

        model.addAttribute("auctionItem", auctionItem);
        model.addAttribute("isSavedAuctionItem", isSavedAuctionItem);
        return "auctionItem";
    }

    @PostMapping("/auctionItem")
    public String saveAuctionItem(HttpServletRequest request) {
        String username = "";

        try {
            username = jwtService.getUsernameByRequest(request);
        } catch (NotFoundException e) {
            return "redirect:/auction/login";
        }

        Bidder bidder = bidderService.findBidderByUsername(username);
        AuctionItem savedAuctionItem = new AuctionItem();
        savedAuctionItem.setAuctionItemText(auctionItemService.makeAuctionItem(bidder));
        savedAuctionItem.setBidder(bidder);

        auctionItemService.saveAuctionItem(savedAuctionItem);
        return "redirect:/auction";
    }
}