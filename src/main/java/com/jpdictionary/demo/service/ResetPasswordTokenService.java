package com.jpdictionary.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpdictionary.demo.models.ResetPasswordToken;
import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.repository.ResetPasswordTokenRepository;
import com.jpdictionary.demo.repository.UserRepository;

@Service
public class ResetPasswordTokenService {

    @Autowired
    private ResetPasswordTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendResetEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        String token = UUID.randomUUID().toString();
        ResetPasswordToken resetToken = new ResetPasswordToken();
        resetToken.setUser(user);
        resetToken.setToken(token);
        resetToken.setExpiryTime(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(resetToken);

        // Gửi email
        String link = "http://localhost:8082/reset-password?token=" + token;
        // Gửi mail với link này (dùng JavaMailSender)
    }

    public void resetPassword(String token, String newPassword) {
        ResetPasswordToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token không hợp lệ"));

        if (resetToken.isUsed())
            throw new RuntimeException("Token đã được sử dụng");

        if (resetToken.getExpiryTime().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Token đã hết hạn");

        User user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);

        resetToken.setUsed(true);
        tokenRepository.save(resetToken);
    }
}
