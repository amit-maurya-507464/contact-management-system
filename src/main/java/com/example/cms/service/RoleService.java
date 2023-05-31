package com.example.cms.service;

import com.example.cms.entity.Privilege;
import com.example.cms.entity.Role;
import com.example.cms.enums.Privileges;
import com.example.cms.enums.Roles;
import com.example.cms.repository.PrivilegeRepository;
import com.example.cms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Role findRole(Roles roles) {
        return roleRepository.findByRoleName(roles);
    }

    public void createSuperAdminRole() {
        List<Privilege> privilegeList = privilegeRepository.findAll();
        Role role = new Role();
        role.setRoleName(Roles.SUPER_ADMIN);
        role.setPrivileges((new HashSet<>(privilegeList)));
        roleRepository.save(role);
    }

    public void createAdminRole() {
        List<Privilege> privilegeList = privilegeRepository.findByNameIn(Arrays.asList(Privileges.CONTACT_WRITE, Privileges.CONTACT_READ, Privileges.CONTACT_GET, Privileges.CONTACT_DELETE));
        Role role = new Role();
        role.setRoleName(Roles.ADMIN);
        role.setPrivileges((new HashSet<>(privilegeList)));
        roleRepository.save(role);
    }

    public void createUserRole() {
        List<Privilege> privilegeList = privilegeRepository.findByNameIn(Arrays.asList(Privileges.CONTACT_WRITE, Privileges.CONTACT_READ));
        Role role = new Role();
        role.setRoleName(Roles.USER);
        role.setPrivileges((new HashSet<>(privilegeList)));
        roleRepository.save(role);
    }
}
