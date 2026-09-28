package com.example.DonateHub.controller;

import com.example.DonateHub.entity.Donor;
import com.example.DonateHub.service.DonorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    // CREATE
    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return donorService.createDonor(donor);
    }

    // GET ALL
    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable Long id,
            @RequestBody Donor donor) {

        return donorService.updateDonor(id, donor);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {

        donorService.deleteDonor(id);

        return "Donor deleted successfully";
    }
}