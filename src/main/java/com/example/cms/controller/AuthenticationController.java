package com.example.cms.controller;

import com.example.cms.constants.MessageCode;
import com.example.cms.constants.MessageConstants;
import com.example.cms.constants.UrlConstants;
import com.example.cms.dto.AuthorizationTokenDTO;
import com.example.cms.dto.UserDTO;
import com.example.cms.service.UserService;
import com.example.cms.utils.CommonUtilsService;
import com.example.cms.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(UrlConstants.BASE_API_V1)
public class AuthenticationController {

    @Autowired
    private CommonUtilsService commonUtilsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping(UrlConstants.SIGN_UP)
    public ResponseEntity<Object> signUp(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        try {
            log.info(MessageConstants.SIGN_UP_REQUEST, userDTO);
            if (bindingResult.hasErrors()) {
                return commonUtilsService.requestValidation(bindingResult);
            }
            if (userService.checkUserNameExist(userDTO.getUserName()))
                return responseHandler.generateResponse("", MessageCode.USER_ALREADY_EXIST, false, HttpStatus.BAD_REQUEST);
            else
                userService.signUpUser(userDTO);
            return responseHandler.generateResponse("", MessageCode.SIGN_UP_SUCCESSFULLY, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.SIGN_UP_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.SIGN_UP_ERROR, false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(UrlConstants.SIGN_IN)
    public ResponseEntity<Object> signIn(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        try {
            log.info(MessageConstants.SIGN_IN_REQUEST, userDTO);
            if (bindingResult.hasErrors()) {
                return commonUtilsService.requestValidation(bindingResult);
            }
            AuthorizationTokenDTO authorizationTokenDTO = userService.signInUser(userDTO);
            if (authorizationTokenDTO!=null) {
                return responseHandler.generateResponse(authorizationTokenDTO, MessageCode.SIGN_IN_SUCCESSFULLY, true, HttpStatus.OK);
            }
            return responseHandler.generateResponse("", MessageCode.USERNAME_PAASWORD_INCORRECT, false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(MessageConstants.SIGN_IN_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.SIGN_IN_ERROR, false, HttpStatus.BAD_REQUEST);
    }

}
