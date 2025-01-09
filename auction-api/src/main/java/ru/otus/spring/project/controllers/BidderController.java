package ru.otus.spring.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.project.dto.BidderDto;
import ru.otus.spring.project.services.BidderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BidderController {

    private final BidderService bidderService;

    @PostMapping("/api/bidders")
    public BidderDto saveBidder(@RequestBody BidderDto bidderDto) {
        return bidderService.insert(bidderDto);
    }

    @GetMapping("/api/bidders/{username}")
    public BidderDto findBidderByUsername(@PathVariable("username") String username) {
        return bidderService.findBidderByUsername(username);
    }

    @PostMapping("/api/bidders/{username}")
    public void deleteBidderByUsername(@PathVariable("username") String username) {
        bidderService.deleteBidderByUsername(username);
    }

    @GetMapping("/api/bidders")
    public List<BidderDto> findAllApprovedBidders() {
        return bidderService.findAllApprovedBidders();
    }
}