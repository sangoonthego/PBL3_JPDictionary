package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);  // Tìm role theo tên
}
