package ru.otus.spring.project.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.project.dto.AuctionRelationshipDto;
import ru.otus.spring.project.models.AuctionRelationship;

@Component
@RequiredArgsConstructor
public class AuctionRelationshipConverter {

    public AuctionRelationshipDto fromDomainObject(AuctionRelationship relationship) {
        AuctionRelationshipDto relationshipDto = new AuctionRelationshipDto();
        relationshipDto.setSourceUsername(relationship.getSourceBidder().getUsername());
        relationshipDto.setSourceName(relationship.getSourceBidder().getName());
        relationshipDto.setTargetUsername(relationship.getTargetBidder().getUsername());
        relationshipDto.setTargetName(relationship.getTargetBidder().getName());
        return relationshipDto;
    }
}