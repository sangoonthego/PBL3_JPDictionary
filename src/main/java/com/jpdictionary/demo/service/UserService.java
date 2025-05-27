package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.models.Role;
import com.jpdictionary.demo.repository.UserRepository;
import com.jpdictionary.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(new Date());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Giả lập API ngoài (trả danh sách user mẫu)
    public List<User> fetchUsersFromAPI() {
        Role defaultRole = roleRepository.findById(2L).orElse(null); // 2L = Student chẳng hạn

        return Arrays.asList(
            new User(null, "user1", "hashed_pass1", "user1@example.com", defaultRole, new Date()),
            new User(null, "user2", "hashed_pass2", "user2@example.com", defaultRole, new Date())
        );
    }

    public void saveUsersFromAPI() {
        List<User> usersFromAPI = fetchUsersFromAPI();
        userRepository.saveAll(usersFromAPI);
    }
}
