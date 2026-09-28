package com.example.DonateHub.service;

import com.example.DonateHub.entity.Donor;
import com.example.DonateHub.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor createDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));
    }

    public Donor updateDonor(Long id, Donor donor) {

        Donor existingDonor = getDonorById(id);

        existingDonor.setName(donor.getName());
        existingDonor.setEmail(donor.getEmail());
        existingDonor.setPhone(donor.getPhone());

        return donorRepository.save(existingDonor);
    }

    public void deleteDonor(Long id) {

        Donor donor = getDonorById(id);
        donorRepository.delete(donor);
    }
}