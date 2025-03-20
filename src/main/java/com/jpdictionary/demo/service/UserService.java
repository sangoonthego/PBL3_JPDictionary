package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Mã hóa mật khẩu

    // Lấy toàn bộ user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy user theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Lấy user theo username (dùng cho login)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Đăng ký user (tạo mới user)
    public User registerUser(User user) {
        // Kiểm tra username hoặc email đã tồn tại chưa
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại!");
        }

        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Xóa user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final String EXTERNAL_API_URL = "https://api.example.com/users"; // Đổi API thật
//
//    // Lấy toàn bộ User từ API
//    public List<User> fetchUsersFromAPI() {
//        User[] users = restTemplate.getForObject(EXTERNAL_API_URL, User[].class);
//        if (users != null) {
//            return List.of(users);
//        }
//        return List.of();
//    }
//
//    // Lưu User vào Database
//    public void saveUsersFromAPI() {
//        List<User> users = fetchUsersFromAPI();
//        userRepository.saveAll(users);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<User> getUserById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public Optional<User> getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//}
