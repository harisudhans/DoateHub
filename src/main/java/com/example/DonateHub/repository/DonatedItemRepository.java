package com.example.DonateHub.repository;

import com.example.DonateHub.entity.DonatedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonatedItemRepository
        extends JpaRepository<DonatedItem, Long> {
}