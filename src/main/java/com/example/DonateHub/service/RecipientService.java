package com.example.DonateHub.service;

import com.example.DonateHub.entity.Recipient;
import com.example.DonateHub.repository.RecipientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {

    private final RecipientRepository recipientRepository;

    public RecipientService(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public Recipient createRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    public List<Recipient> getAllRecipients() {
        return recipientRepository.findAll();
    }

    public Recipient getRecipientById(Long id) {
        return recipientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));
    }

    public Recipient updateRecipient(Long id, Recipient recipient) {

        Recipient existingRecipient = getRecipientById(id);

        existingRecipient.setOrganizationName(
                recipient.getOrganizationName());

        existingRecipient.setContactPerson(
                recipient.getContactPerson());

        existingRecipient.setPhone(
                recipient.getPhone());

        existingRecipient.setAddress(
                recipient.getAddress());

        return recipientRepository.save(existingRecipient);
    }

    public void deleteRecipient(Long id) {

        Recipient recipient = getRecipientById(id);

        recipientRepository.delete(recipient);
    }
}