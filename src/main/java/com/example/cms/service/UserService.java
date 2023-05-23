package com.example.cms.service;

import com.example.cms.constants.MessageCode;
import com.example.cms.constants.MessageConstants;
import com.example.cms.dto.ApiResponse;
import com.example.cms.dto.AuthorizationTokenDTO;
import com.example.cms.dto.UserDTO;
import com.example.cms.entity.Role;
import com.example.cms.entity.User;
import com.example.cms.enums.Roles;
import com.example.cms.repository.UserRepository;
import com.example.cms.security.JwtTokenProvider;
import com.example.cms.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    @Autowired
    private ResponseHandler responseHandler;

    public ResponseEntity<ApiResponse> signUpUser(UserDTO userDTO) {
        if (checkUserNameExist(userDTO.getUserName())) {
            return responseHandler.generateResponse("", MessageCode.USER_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findRole(Roles.USER));
        user.setRoles(roleSet);
        userRepository.save(user);
        return responseHandler.generateResponse("", MessageCode.SIGN_UP_SUCCESSFULLY, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> signInUser(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
            AuthorizationTokenDTO authorizationTokenDTO = new AuthorizationTokenDTO(userDTO.getUserName(), jwtTokenProvider.createAuthToken(userDTO.getUserName()));
            return responseHandler.generateResponse(authorizationTokenDTO, MessageCode.SIGN_IN_SUCCESSFULLY, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.error(MessageConstants.USERNAME_PASSWORD_INCORRECT, e);
            return responseHandler.generateResponse("", MessageCode.USERNAME_PAASWORD_INCORRECT, HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkUserNameExist(String userName) {
        return userRepository.existsByUserName(userName);
    }
}
