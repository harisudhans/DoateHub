package com.example.DonateHub.controller;

import com.example.DonateHub.entity.Drive;
import com.example.DonateHub.service.DriveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class DriveController {

    private final DriveService driveService;

    public DriveController(DriveService driveService) {
        this.driveService = driveService;
    }

    // CREATE
    @PostMapping
    public Drive createDrive(@RequestBody Drive drive) {
        return driveService.createDrive(drive);
    }

    // GET ALL
    @GetMapping
    public List<Drive> getAllDrives() {
        return driveService.getAllDrives();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Drive getDriveById(@PathVariable Long id) {
        return driveService.getDriveById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Drive updateDrive(
            @PathVariable Long id,
            @RequestBody Drive drive) {

        return driveService.updateDrive(id, drive);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteDrive(@PathVariable Long id) {

        driveService.deleteDrive(id);

        return "Drive deleted successfully";
    }
}