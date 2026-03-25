package com.crypto.encryption_system.service;

import com.crypto.encryption_system.model.User;
import com.crypto.encryption_system.repository.UserRepository;
import com.crypto.encryption_system.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ================= REGISTER =================
    public String registerUser(User user) {
        try {
            String email = user.getEmail().toLowerCase(); // always lowercase
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isPresent()) {
                return "Email already registered!";
            }

            // Encrypt password before saving
            String encryptedPassword = AESUtil.encrypt(user.getPassword());
            if (encryptedPassword == null) return "Server error during registration!";

            user.setEmail(email);
            user.setPassword(encryptedPassword);

            userRepository.save(user);

            return "Registration successful!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Server error during registration!";
        }
    }

    // ================= LOGIN =================
    public String loginUser(User user) {
        try {
            String email = user.getEmail().toLowerCase(); // lowercase
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isEmpty()) return "User not found!";

            String encryptedPassword = AESUtil.encrypt(user.getPassword());
            if (!existingUser.get().getPassword().equals(encryptedPassword)) {
                return "Invalid password!";
            }

            return "Login successful!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Server error during login!";
        }
    }
}
