package ru.otus.spring.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.project.models.AuctionItem;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {

    @Query("SELECT ai FROM Bidder b INNER JOIN b.auctionItem ai where b.username = :username")
    AuctionItem findAuctionItemByUsername(@Param("username") String username);
}