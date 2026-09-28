package com.example.DonateHub.repository;

import com.example.DonateHub.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository
        extends JpaRepository<Recipient, Long> {
}