package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Permission;
import com.jpdictionary.demo.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    private final String API_URL = "https://api.example.com/permissions"; // Thay bằng API thật

    public List<Permission> getPermissionsFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        Permission[] permissionsList = restTemplate.getForObject(API_URL, Permission[].class);
        
        if (permissionsList != null) {
            permissionRepository.saveAll(List.of(permissionsList));  // Lưu vào database
        }
        
        return permissionRepository.findAll();
    }
}
