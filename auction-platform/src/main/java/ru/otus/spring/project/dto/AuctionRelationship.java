package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuctionRelationship {

    private String sourceUsername;

    private String sourceName;

    private String targetUsername;

    private String targetName;
}