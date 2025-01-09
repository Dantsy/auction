package ru.otus.spring.project.services;

import ru.otus.spring.project.dto.BidderDto;

import java.util.List;

public interface BidderService {

    BidderDto insert(BidderDto bidderDto);

    BidderDto findBidderByUsername(String username);

    void deleteBidderByUsername(String username);

    List<BidderDto> findAllApprovedBidders();
}