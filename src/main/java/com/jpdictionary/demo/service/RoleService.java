package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Role;
import com.jpdictionary.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role newRole) {
        return roleRepository.findById(id).map(role -> {
            role.setName(newRole.getName());
            return roleRepository.save(role);
        }).orElse(null);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
