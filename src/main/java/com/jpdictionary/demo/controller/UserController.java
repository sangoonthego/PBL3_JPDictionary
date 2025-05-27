package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id);
            updatedUser.setCreatedAt(existingUser.get().getCreatedAt()); // giữ nguyên createdAt
            return ResponseEntity.ok(userService.saveUser(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/fetch-external")
    public List<User> fetchUsersFromAPI() {
        return userService.fetchUsersFromAPI();
    }

    @PostMapping("/save-external")
    public String saveUsersFromAPI() {
        userService.saveUsersFromAPI();
        return "Users fetched and saved successfully!";
    }
}



