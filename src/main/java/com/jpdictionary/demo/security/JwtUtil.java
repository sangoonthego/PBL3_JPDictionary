package com.jpdictionary.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "your-secret-key"; // Đổi thành key mạnh hơn trong thực tế

    // Phương thức tạo token
    public String generateToken(String username) {
        // 10 giờ
        long EXPIRATION_TIME = 1000 * 60 * 60 * 10;
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // Phương thức lấy username từ token
    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }

    // Phương thức kiểm tra tính hợp lệ của token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username);
    }

    // Kiểm tra xem token có hết hạn không
    public boolean isTokenExpired(String token) {
        Date expirationDate = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getExpiresAt();
        return expirationDate.before(new Date());
    }

    // Phương thức validate token và lấy username
    public String validateTokenAndGetUsername(String token) {
        try {
            String username = extractUsername(token);
            if (isTokenExpired(token)) {
                throw new JWTVerificationException("Token đã hết hạn");
            }
            return username;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token không hợp lệ");
        }
    }
}
