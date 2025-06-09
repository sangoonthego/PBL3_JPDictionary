package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.service.ResetPasswordTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/password")
public class ResetPasswordTokenController {

    @Autowired
    private ResetPasswordTokenService resetPasswordService;

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        resetPasswordService.sendResetEmail(email);
        return ResponseEntity.ok("Email khôi phục đã được gửi nếu tồn tại.");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String newPassword) {
        resetPasswordService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Đặt lại mật khẩu thành công.");
    }
}

