package com.example.cms.dto;

import com.example.cms.constants.ValidationMessageCode;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ContactDTO {

    private long id;

    @NotBlank(message = ValidationMessageCode.FIRST_NAME_BLANK)
    private String firstName;

    @NotBlank(message = ValidationMessageCode.LAST_NAME_BLANK)
    private String lastName;

    @NotBlank(message = ValidationMessageCode.EMAIL_BLANK)
    @Email(message = ValidationMessageCode.EMAIL_INVALID)
    private String email;

    @NotBlank(message = ValidationMessageCode.PHONE_BLANK)
    private String phoneNumber;
}
