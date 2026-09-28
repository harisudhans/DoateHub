package com.example.DonateHub.service;

import com.example.DonateHub.entity.DonatedItem;
import com.example.DonateHub.entity.Drive;
import com.example.DonateHub.entity.Donor;
import com.example.DonateHub.entity.Recipient;
import com.example.DonateHub.repository.DonatedItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonatedItemService {

    private final DonatedItemRepository itemRepository;
    private final DriveService driveService;
    private final DonorService donorService;
    private final RecipientService recipientService;

    public DonatedItemService(
            DonatedItemRepository itemRepository,
            DriveService driveService,
            DonorService donorService,
            RecipientService recipientService) {

        this.itemRepository = itemRepository;
        this.driveService = driveService;
        this.donorService = donorService;
        this.recipientService = recipientService;
    }

    public DonatedItem createItem(
            DonatedItem item,
            Long driveId,
            Long donorId) {

        // Check category
        if (!item.getCategory().equalsIgnoreCase("BOOKS")
                && !item.getCategory().equalsIgnoreCase("CLOTHES")) {

            throw new RuntimeException(
                    "Category must be BOOKS or CLOTHES");
        }

        // Check condition
        if (!item.getItemCondition().equalsIgnoreCase("NEW")
                && !item.getItemCondition().equalsIgnoreCase("GOOD")
                && !item.getItemCondition().equalsIgnoreCase("WORN")) {

            throw new RuntimeException(
                    "Condition must be NEW, GOOD or WORN");
        }

        Drive drive = driveService.getDriveById(driveId);

        Donor donor = donorService.getDonorById(donorId);

        item.setDrive(drive);
        item.setDonor(donor);

        // Initially item is not distributed
        item.setDistributed(false);

        return itemRepository.save(item);
    }

    public List<DonatedItem> getAllItems() {
        return itemRepository.findAll();
    }

    public DonatedItem getItemById(Long id) {

        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));
    }

    public List<DonatedItem> getAvailableItems() {

        return itemRepository.findAll()
                .stream()
                .filter(item -> !item.isDistributed())
                .toList();
    }

    public DonatedItem distributeItem(
            Long itemId,
            Long recipientId) {

        DonatedItem item = getItemById(itemId);

        // Business Rule:
        // An item can be distributed only once
        if (item.isDistributed()) {

            throw new RuntimeException(
                    "This item has already been distributed");
        }

        Recipient recipient =
                recipientService.getRecipientById(recipientId);

        item.setRecipient(recipient);

        item.setDistributed(true);

        return itemRepository.save(item);
    }
}