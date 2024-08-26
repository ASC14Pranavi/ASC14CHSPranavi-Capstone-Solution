package com.elt.service;

import com.elt.entity.LoginDetails;
import com.elt.model.LoginDto;
import com.elt.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String login(String email, String password) {
        Optional<LoginDetails> optionalUserEntity = loginRepository.findByEmail(email);
        if (optionalUserEntity.isPresent()) {
            LoginDetails userEntity = optionalUserEntity.get();

            // Check if the account is locked and if the lock time has passed
            if (userEntity.isAccountLocked() && userEntity.getLockTime() != null
                    && userEntity.getLockTime().isAfter(LocalDateTime.now())) {
                return "Account is locked. Please try again later.";
            }

            if (validatePassword(password, userEntity.getPassword())) {
                resetFailedLoginAttempts(userEntity); // Reset on successful login
                return "Login successful.";
            } else {
                increaseFailedLoginAttempts(userEntity);
                int failedAttempts = userEntity.getFailedAttempts();
                if (failedAttempts >= 3) {
                    return "Invalid password. Account locked for 30 minutes.";
                } else {
                    return "Invalid password. Attempt " + failedAttempts + " of 3.";
                }
            }
        } else {
            return "Invalid email.";
        }
    }

    private boolean validatePassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword); // Consider using a more secure password hashing mechanism
    }

    private void increaseFailedLoginAttempts(LoginDetails user) {
        user.setFailedAttempts(user.getFailedAttempts() + 1);
        if (user.getFailedAttempts() >= 3) {
            user.setAccountLocked(true);
            user.setLockTime(LocalDateTime.now().plusMinutes(30));
        }
        loginRepository.save(user);
    }

    private void resetFailedLoginAttempts(LoginDetails user) {
        user.setFailedAttempts(0);
        user.setAccountLocked(false);
        user.setLockTime(null); // Clear lock time
        loginRepository.save(user);
    }

    public LoginDetails convertToEntity(LoginDto userDTO) {
        LoginDetails user = new LoginDetails();
        user.setEmail(userDTO.getEmail());
        user.setUserId(userDTO.getUserId());
        user.setPassword(userDTO.getPassword());
        user.setAccountLocked(userDTO.isAccountLocked());
        user.setFailedAttempts(userDTO.getFailedAttempts());
        user.setLockTime(userDTO.getLockTime()); // Ensure the DTO's lockTime is managed correctly
        return user;
    }
}
