package ru.otus.spring.project.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.spring.project.dto.Bidder;
import ru.otus.spring.project.services.BidderService;
import ru.otus.spring.project.services.JwtService;

import java.util.Arrays;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionMainController {

    private final BidderService bidderService;

    private final JwtService jwtService;

    @GetMapping("")
    public String auctionPage(HttpServletRequest request, Model model) {
        boolean isAuthorized = false;
        boolean isSavedBidder = false;
        Bidder bidder = null;

        if (request.getCookies() != null) {
            Optional<String> optionalToken = readCookie(request, "Authorization");
            if (optionalToken.isPresent()) {
                isAuthorized = true;
                log.info("We check whether the auction participant is registered");
                String username = jwtService.getUsernameByRequest(request);
                bidder = bidderService.findBidderByUsername(username);
            }
        }

        if (bidder != null) {
            isSavedBidder = true;
        }

        model.addAttribute("isAuthorized", isAuthorized);
        model.addAttribute("isSavedBidder", isSavedBidder);
        return "auction";
    }

    private Optional<String> readCookie(HttpServletRequest request, String key) {
        return Arrays.stream(request.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
