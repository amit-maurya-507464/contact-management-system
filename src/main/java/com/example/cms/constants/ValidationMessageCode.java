package com.example.cms.constants;

public class ValidationMessageCode {

    private ValidationMessageCode() {
        throw new IllegalArgumentException("ValidationMessageCode is a utility class");
    }

    public static final String FIRST_NAME_BLANK = "{first.name.blank}";
    public static final String LAST_NAME_BLANK = "{last.name.blank}";
    public static final String EMAIL_BLANK = "{email.blank}";
    public static final String EMAIL_INVALID = "{email.invalid}";
    public static final String PHONE_BLANK = "{phone.blank}";

    public static final String USER_NAME_BLANK = "{username.blank}";
    public static final String PASSWORD_BLANK = "{password.blank}";
}
