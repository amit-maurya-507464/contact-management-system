package com.example.cms.service;

import com.example.cms.entity.Role;
import com.example.cms.enums.Roles;
import com.example.cms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findRole(Roles roles) {
        return roleRepository.findByRoleName(roles);
    }
}
