package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Role;
import com.jpdictionary.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", "Role not found"));
        }
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            Role updatedRole = roleService.updateRole(id, role);
            return ResponseEntity.ok(updatedRole);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Role deleted successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
