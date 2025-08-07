package com.audio.audiotranscribe.controller;

import com.audio.audiotranscribe.auth.LoginRequest;
import com.audio.audiotranscribe.auth.LoginResponse;
import com.audio.audiotranscribe.model.User;
import com.audio.audiotranscribe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final UserService userService;

    // ✅ Signup user
    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    // ✅ Login and return JWT token
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);  // This returns a JSON with the token
    }
}
