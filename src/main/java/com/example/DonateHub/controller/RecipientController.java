package com.example.DonateHub.controller;

import com.example.DonateHub.entity.Recipient;
import com.example.DonateHub.service.RecipientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipients")
public class RecipientController {

    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    // CREATE
    @PostMapping
    public Recipient createRecipient(
            @RequestBody Recipient recipient) {

        return recipientService.createRecipient(recipient);
    }

    // GET ALL
    @GetMapping
    public List<Recipient> getAllRecipients() {

        return recipientService.getAllRecipients();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Recipient getRecipientById(
            @PathVariable Long id) {

        return recipientService.getRecipientById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Recipient updateRecipient(
            @PathVariable Long id,
            @RequestBody Recipient recipient) {

        return recipientService.updateRecipient(id, recipient);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteRecipient(
            @PathVariable Long id) {

        recipientService.deleteRecipient(id);

        return "Recipient deleted successfully";
    }
}
