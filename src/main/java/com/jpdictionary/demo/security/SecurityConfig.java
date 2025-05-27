package com.jpdictionary.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;  // Thêm JwtAuthenticationEntryPoint

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Cho phép tất cả các request đến api/auth
                        .requestMatchers("/student/**").hasRole("USER")  // Phân quyền ROLE_USER
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Phân quyền ROLE_ADMIN
                        .anyRequest().authenticated())  // Các request khác phải xác thực
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)  // Xử lý lỗi khi chưa đăng nhập hoặc token không hợp lệ
                        .accessDeniedHandler(accessDeniedHandler()))  // Sử dụng AccessDeniedHandler để xử lý lỗi 403
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Không dùng session
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // Thêm JwtFilter để kiểm tra token
                .build();
    }
    
//    @Bean
//    SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable()) // Tắt CSRF để test API
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().permitAll() // Cho phép mọi request
//            );
//
//        return http.build();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Mã hóa mật khẩu bằng BCrypt
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();  
    }

    // Bean để xử lý Access Denied 403
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();  // Cấu hình xử lý lỗi 403 khi không có quyền truy cập
    }
}
