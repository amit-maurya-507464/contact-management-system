package com.example.cms.constants;

public class MessageCode {

    private MessageCode() {
        throw new IllegalArgumentException("MessageCode is a utility class");
    }

    public static final String CONTACT_CREATED = "contact.created";
    public static final String CONTACT_NOT_FOUND = "contact.not.found";
    public static final String CONTACT_EDITED = "contact.edited";
    public static final String CONTACT_DELETED = "contact.deleted";
    public static final String CONTACT_FETCHED = "contact.fetched";
    public static final String CONTACT_EXIST_EMAIL = "contact.exist.email";

    public static final String CONTACT_CREATE_ERROR = "contact.created.error";
    public static final String CONTACT_EDIT_ERROR = "contact.edit.error";
    public static final String CONTACT_DELETE_ERROR = "contact.delete.error";
    public static final String CONTACT_GET_ERROR = "contact.get.error";
    public static final String SIGN_UP_ERROR = "sign.up.error";
    public static final String SIGN_IN_ERROR = "sign.in.error";

    public static final String SIGN_UP_SUCCESSFULLY = "sign.up.successfully";
    public static final String USER_ALREADY_EXIST = "user.already.exist";
    public static final String SIGN_IN_SUCCESSFULLY = "sign.in.successfully";

    public static final String USERNAME_PAASWORD_INCORRECT = "username.password.incorrect";

}
