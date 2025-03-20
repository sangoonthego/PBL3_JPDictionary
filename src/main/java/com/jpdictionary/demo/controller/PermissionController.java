package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Permission;
import com.jpdictionary.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/fetch")
    public List<Permission> fetchPermissions() {
        return permissionService.getPermissionsFromAPI();
    }
}
