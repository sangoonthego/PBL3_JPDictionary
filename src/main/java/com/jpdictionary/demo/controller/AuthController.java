package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.LoginRequest;
import com.jpdictionary.demo.models.RegisterRequest;
import com.jpdictionary.demo.models.Role;
import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.repository.RoleRepository;
import com.jpdictionary.demo.repository.UserRepository;
import com.jpdictionary.demo.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String token = jwtUtil.generateToken(request.getUsername());
            System.out.println("Token sau khi đăng nhập: " + token);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Sai tài khoản hoặc mật khẩu!");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Tài khoản đã tồn tại.");
        }

        // Gán role mặc định
        Role role = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Không tìm thấy ROLE_USER"));

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole(role);
        newUser.setCreatedAt(new Date());

        userRepository.save(newUser);

        return ResponseEntity.ok("Đăng ký thành công!");
    }

}
