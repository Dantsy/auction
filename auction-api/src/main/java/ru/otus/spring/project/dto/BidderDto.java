package ru.otus.spring.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BidderDto {
    private Long id;

    private String name;

    private String username;

    private String present;

    private List<AnswerDto> answers;

    private AuctionItemDto auctionItem;

    @Override
    public String toString() {
        return "BidderDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", present='" + present + '\'' +
                ", answers=" + answers +
                '}';
    }
}
