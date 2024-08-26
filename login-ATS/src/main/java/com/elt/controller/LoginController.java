package com.elt.controller;

import com.elt.service.LoginService;
import com.elt.feignclient.BookingFeignClient;
import com.elt.model.BookingDto;
import com.elt.model.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;
    private final BookingFeignClient bookingFeignClient;

    @Autowired
    public LoginController(LoginService loginService, BookingFeignClient bookingFeignClient) {
        this.loginService = loginService;
        this.bookingFeignClient = bookingFeignClient;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        // Check if both email and password are provided
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return new ResponseEntity<>("Email and password are required.", HttpStatus.BAD_REQUEST);
        }

        // Call the login service to handle the login logic
        String result = loginService.login(email, password);

        if (result.equals("Login successful.")) {
            // Fetch all bookings if login is successful
            List<BookingDto> bookings = bookingFeignClient.getAllBookings();
            return ResponseEntity.ok(bookings);
        } else if (result.contains("locked")) {
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        } else if (result.contains("Invalid email")) {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}
