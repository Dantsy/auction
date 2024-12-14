package ru.otus.spring.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bidder {
    @NotBlank(message = "Name must be filled in")
    private String name;

    @NotBlank(message = "Auction item must be completed")
    private String auctionItemName;

    private String username;

    private List<Answer> answers;

    private AuctionItem auctionItem;

    @Override
    public String toString() {
        return "Bidder{" +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", auctionItemName='" + auctionItemName + '\'' +
                ", answers=" + answers +
                '}';
    }
}