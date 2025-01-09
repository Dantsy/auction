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
import ru.otus.spring.project.dto.AuctionRelationship;
import ru.otus.spring.project.services.BidderService;
import ru.otus.spring.project.services.JwtService;
import ru.otus.spring.project.services.AuctionRelationshipService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionAdminController {

    private final JwtService jwtService;

    private final BidderService bidderService;

    private final AuctionRelationshipService auctionRelationshipService;

    @GetMapping("/admin")
    public String adminPage(HttpServletRequest request, Model model) {
        boolean isDistributedAuctionItem = false;
        String username;
        try {
            username = jwtService.getUsernameByRequest(request);
        } catch (NotFoundException e) {
            return "redirect:/auction/login";
        }
        if (!username.equals("admin")) {
            return "redirect:/auction";
        }
        List<AuctionRelationship> auctionRelationships = auctionRelationshipService.findAllAuctionRelationships();
        if (auctionRelationships.size() != 0) {
            isDistributedAuctionItem = true;
        }
        List<Bidder> bidders = bidderService.findAllApprovedBidders();
        model.addAttribute("bidders", bidders);
        model.addAttribute("isDistributedAuctionItem", isDistributedAuctionItem);
        model.addAttribute("auctionRelationships", auctionRelationships);
        return "admin";
    }

    @PostMapping("/admin")
    public String distributeAuctionItems() {
        auctionRelationshipService.distributeAuctionItems();
        return "redirect:/auction/admin";
    }
}