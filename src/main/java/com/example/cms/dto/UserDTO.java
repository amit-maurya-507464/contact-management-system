package com.example.cms.dto;

import com.example.cms.constants.ValidationMessageCode;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank(message = ValidationMessageCode.USER_NAME_BLANK)
    private String userName;
    @NotBlank(message = ValidationMessageCode.PASSWORD_BLANK)
    private String password;
}
