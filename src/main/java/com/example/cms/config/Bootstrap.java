package com.example.cms.config;

import com.example.cms.constants.MessageConstants;
import com.example.cms.entity.Role;
import com.example.cms.entity.User;
import com.example.cms.enums.Roles;
import com.example.cms.repository.RoleRepository;
import com.example.cms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@Slf4j
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${admin.username}")
	private String adminUsername;
	@Value("${admin.password}")
	private String adminPassword;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info(MessageConstants.APPLICATION_EVENT_METHOD);
		try {
			createDefaultRolesIfNotExist();
			createSuperAdminIfNotExist();
		} catch (Exception e) {
			log.error(MessageConstants.APPLICATION_EVENT_ERROR, e);
		}
	}

	public void createDefaultRolesIfNotExist() {
		log.info(MessageConstants.DEFAULT_ROLES_CREATE);
		try {
			List<Role> existingRoles = roleRepository.findAll();
			if (existingRoles.isEmpty()) {
				Set<Role> roles = new HashSet<>();
				Role roleAdmin = new Role(Roles.ADMIN);
				Role roleUser = new Role(Roles.USER);
				roles.add(roleAdmin);
				roles.add(roleUser);
				roleRepository.saveAll(roles);
			}
		} catch (Exception e) {
			log.error(MessageConstants.DEFAULT_ROLES_CREATE_ERROR, e);
		}
	}

	private void createSuperAdminIfNotExist() {
		log.info(MessageConstants.SUPER_ADMIN_CREATE);
		try {
			User adminUser = userRepository.findByUserName(adminUsername);
			if (adminUser==null) {
				User user = new User();
				user.setUserName(adminUsername);
				user.setPassword(passwordEncoder.encode(adminPassword));
				Set<Role> roles = new HashSet<>();
				Role adminRole = roleRepository.findByRoleName(Roles.ADMIN);
				Role userRole = roleRepository.findByRoleName(Roles.USER);
				roles.add(adminRole);
				roles.add(userRole);
				user.setRoles(roles);
				userRepository.save(user);
			}
		} catch (Exception e) {
			log.error(MessageConstants.SUPER_ADMIN_CREATE_ERROR, e);
		}
	}

}
