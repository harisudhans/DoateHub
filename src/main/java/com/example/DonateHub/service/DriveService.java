package com.example.DonateHub.service;

import com.example.DonateHub.entity.Drive;
import com.example.DonateHub.repository.DriveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriveService {

    private final DriveRepository driveRepository;

    public DriveService(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public Drive createDrive(Drive drive) {
        return driveRepository.save(drive);
    }

    public List<Drive> getAllDrives() {
        return driveRepository.findAll();
    }

    public Drive getDriveById(Long id) {
        return driveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drive not found"));
    }

    public Drive updateDrive(Long id, Drive drive) {

        Drive existingDrive = getDriveById(id);

        existingDrive.setName(drive.getName());
        existingDrive.setStartDate(drive.getStartDate());
        existingDrive.setEndDate(drive.getEndDate());

        return driveRepository.save(existingDrive);
    }

    public void deleteDrive(Long id) {
        Drive drive = getDriveById(id);
        driveRepository.delete(drive);
    }
}
