package com.example.DonateHub.controller;

import com.example.DonateHub.entity.DonatedItem;
import com.example.DonateHub.service.DonatedItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class DonatedItemController {

    private final DonatedItemService itemService;

    public DonatedItemController(DonatedItemService itemService) {
        this.itemService = itemService;
    }

    // CREATE DONATED ITEM
    @PostMapping
    public DonatedItem createItem(
            @RequestBody DonatedItem item,
            @RequestParam Long driveId,
            @RequestParam Long donorId) {

        return itemService.createItem(
                item,
                driveId,
                donorId);
    }

    // GET ALL ITEMS
    @GetMapping
    public List<DonatedItem> getAllItems() {

        return itemService.getAllItems();
    }

    // GET ITEM BY ID
    @GetMapping("/{id}")
    public DonatedItem getItemById(
            @PathVariable Long id) {

        return itemService.getItemById(id);
    }

    // GET AVAILABLE ITEMS
    @GetMapping("/available")
    public List<DonatedItem> getAvailableItems() {

        return itemService.getAvailableItems();
    }

    // DISTRIBUTE ITEM
    @PutMapping("/{itemId}/distribute/{recipientId}")
    public DonatedItem distributeItem(
            @PathVariable Long itemId,
            @PathVariable Long recipientId) {

        return itemService.distributeItem(
                itemId,
                recipientId);
    }
}