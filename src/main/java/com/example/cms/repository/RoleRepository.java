package com.example.cms.repository;

import com.example.cms.entity.Role;
import com.example.cms.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(Roles roleName);

}