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
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            resetPasswordService.sendResetEmail(email);
            return ResponseEntity.ok("Email khôi phục đã được gửi nếu tồn tại.");
        } catch (Exception e) {
            // Có thể log lỗi ở đây
            return ResponseEntity.status(500).body("Lỗi hệ thống, vui lòng thử lại sau.");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String token,
                                           @RequestParam String newPassword) {
        try {
            resetPasswordService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Đặt lại mật khẩu thành công.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());  // lỗi token sai, hết hạn...
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống, vui lòng thử lại sau.");
        }
    }
}

