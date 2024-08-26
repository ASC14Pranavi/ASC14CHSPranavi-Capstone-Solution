package com.elt.service;

import com.elt.entity.Admin;
import com.elt.model.AdminDto;
import com.elt.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public String registerAdmin(AdminDto adminDto) {
        if (adminRepository.existsByEmail(adminDto.getEmail())) {
            return "User exists";
        }
        if (adminRepository.existsByPhoneNumber(adminDto.getPhoneNumber())) {
            return "Phone number exists";
        }

        Admin admin = convertDtoToEntity(adminDto);
        String newUserId = generateUserId();
        admin.setUserId(newUserId);

        adminRepository.save(admin);
        return "User registered successfully";
    }

    private String generateUserId() {
        long count = adminRepository.count();
        return String.format("UI%04d", count + 1);
    }

    private Admin convertDtoToEntity(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setUserId(adminDto.getUserId());
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getPassword());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        admin.setFailedAttempts(adminDto.getFailedAttempts());
        admin.setAccountLocked(adminDto.isAccountLocked());
        admin.setLockTime(adminDto.getLockTime());
        admin.setLastLogin(adminDto.getLastLogin());
        return admin;
    }

    public AdminDto convertEntityToDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setUserId(admin.getUserId());
        adminDto.setName(admin.getName());
        adminDto.setEmail(admin.getEmail());
        adminDto.setPassword(admin.getPassword());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        adminDto.setFailedAttempts(admin.getFailedAttempts());
        adminDto.setAccountLocked(admin.isAccountLocked());
        adminDto.setLockTime(admin.getLockTime());
        adminDto.setLastLogin(admin.getLastLogin());
        return adminDto;
    }
}
