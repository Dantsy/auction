package ru.otus.spring.project.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.spring.project.dto.AuctionItem;
import ru.otus.spring.project.services.BidderService;
import ru.otus.spring.project.services.JwtService;
import ru.otus.spring.project.services.AuctionItemService;

@Controller
@Slf4j
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionCabinetController {

    private final BidderService bidderService;

    private final JwtService jwtService;

    private final AuctionItemService auctionItemService;

    @GetMapping("/cabinet")
    public String cabinetPage(HttpServletRequest request, Model model) {
        String username;
        boolean isDistributedAuctionItem = false;
        try {
            username = jwtService.getUsernameByRequest(request);
        } catch (NotFoundException e) {
            return "redirect:/auction/login";
        }
        AuctionItem auctionItem = auctionItemService.findAuctionItemByUsername(username);
        if (auctionItem == null) {
            return "redirect:/auction/auction";
        }
        AuctionItem targetAuctionItem = auctionItemService.findTargetAuctionItemByUsername(username);
        if (targetAuctionItem != null) {
            isDistributedAuctionItem = true;
        }
        model.addAttribute("isDistributedAuctionItem", isDistributedAuctionItem);
        model.addAttribute("auctionItem", targetAuctionItem);
        return "cabinet";
    }

    @PostMapping("/cabinet")
    public String deleteBidder(HttpServletRequest request, HttpServletResponse response) {
        String username;

        try {
            username = jwtService.getUsernameByRequest(request);
        } catch (NotFoundException e) {
            return "redirect:/auction/login";
        }

        Cookie authorizationCookieRemove = new Cookie("Authorization", "");
        authorizationCookieRemove.setMaxAge(0);
        response.addCookie(authorizationCookieRemove);

        bidderService.deleteBidderByUsername(username);
        return "redirect:/auction";
    }
}