package com.audio.audiotranscribe.services;

import com.audio.audiotranscribe.auth.JwtService;
import com.audio.audiotranscribe.auth.LoginRequest;
import com.audio.audiotranscribe.auth.LoginResponse;
import com.audio.audiotranscribe.model.User;
import com.audio.audiotranscribe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // for hashing
    private final JwtService jwtService;

    // ✅ Register new user
    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        // 🔐 Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Login with email + password match
    public String loginUser(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            // Compare raw password with hashed one in DB
            boolean passwordMatch = passwordEncoder.matches(password, existingUser.get().getPassword());

            if (passwordMatch) {
                return "Login successful!";
            } else {
                return "Invalid password!";
            }
        }

        return "User not found!";
    }

    public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtService.generateToken(user.getEmail());
    return new LoginResponse(token);
}




}
