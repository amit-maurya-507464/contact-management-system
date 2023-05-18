package com.example.cms.service;

import com.example.cms.constants.MessageConstants;
import com.example.cms.dto.AuthorizationTokenDTO;
import com.example.cms.dto.UserDTO;
import com.example.cms.entity.Role;
import com.example.cms.entity.User;
import com.example.cms.enums.Roles;
import com.example.cms.repository.UserRepository;
import com.example.cms.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void signUpUser(@Valid UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findRole(Roles.USER));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public AuthorizationTokenDTO signInUser(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
            return new AuthorizationTokenDTO(userDTO.getUserName(), jwtTokenProvider.createAuthToken(userDTO.getUserName()));
        } catch (AuthenticationException e) {
            log.error(MessageConstants.USERNAME_PASSWORD_INCORRECT, e);
            return null;
        }

    }

    public boolean checkUserNameExist(String userName) {
        return userRepository.existsByUserName(userName);
    }
}
