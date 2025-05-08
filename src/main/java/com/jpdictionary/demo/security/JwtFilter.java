package com.jpdictionary.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Lấy token từ header Authorization
        String authHeader = request.getHeader("Authorization");

        // Kiểm tra nếu có header Authorization và token bắt đầu bằng "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Lấy token sau "Bearer "

            try {
                // Xác thực token và lấy username
                String username = jwtUtil.validateTokenAndGetUsername(token);
                if (username != null) {
                    // Lấy thông tin người dùng từ service
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // Tạo đối tượng Authentication token và lưu vào SecurityContextHolder
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Lưu thông tin Authentication vào context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Nếu có lỗi trong quá trình xác thực token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or Expired JWT Token");
                return;
            }
        }

        // Tiếp tục với các filter khác
        filterChain.doFilter(request, response);
    }
}
