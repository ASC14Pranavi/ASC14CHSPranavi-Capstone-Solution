package com.elt.controller;

import com.elt.model.AdminDto;
import com.elt.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/signup")
public class AdminController {

    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<String> registerUser(@RequestBody AdminDto adminDto) {
        if (adminDto.getEmail() == null || adminDto.getEmail().trim().isEmpty() ||
                adminDto.getPassword() == null || adminDto.getPassword().trim().isEmpty() ||
                adminDto.getName() == null || adminDto.getName().trim().isEmpty() ||
                adminDto.getPhoneNumber() == null || adminDto.getPhoneNumber().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required.");
        }

        String nameRegex = "^[A-Za-z ]+$";
        Pattern namePattern = Pattern.compile(nameRegex);
        if (!namePattern.matcher(adminDto.getName()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Name must contain only alphabetic characters and spaces.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (!emailPattern.matcher(adminDto.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email format.");
        }

        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        if (!passwordPattern.matcher(adminDto.getPassword()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a digit, and a special character.");
        }

        if (adminDto.getPhoneNumber().length() != 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number must be 10 digits long.");
        }

        String result = adminService.registerAdmin(adminDto);
        if (result.equals("User exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
        } else if (result.equals("Phone number exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number already exists.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
