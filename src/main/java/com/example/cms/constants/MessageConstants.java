package com.example.cms.constants;

public class MessageConstants {

    private MessageConstants() {
        throw new IllegalArgumentException("MessageConstants is a utility class");
    }

    public static final String CONTACT_CREATE_REQUEST = "Contact create request received:: {}";
    public static final String CONTACT_EDIT_REQUEST = "Contact edit request received:: {}";
    public static final String CONTACT_DELETE_REQUEST = "Contact delete request received:: {}";
    public static final String CONTACT_GET_REQUEST = "Contact get request received:: {}";
    public static final String CONTACT_SEARCH_REQUEST = "Contact search request received:: {}";
    public static final String SIGN_UP_REQUEST = "Sign up request received:: {}";
    public static final String SIGN_IN_REQUEST = "Sign in request received:: {}";
    public static final String ALL_CONTACT_GET_REQUEST = "All Contact get request received :: limit {}, page {}";

    public static final String CONTACT_CREATE_ERROR = "Error while creating contact";
    public static final String CONTACT_EDIT_ERROR = "Error while editing contact";
    public static final String CONTACT_DELETE_ERROR = "Error while deleting contact";
    public static final String CONTACT_GET_ERROR = "Error while getting contact";
    public static final String CONTACT_SEARCH_ERROR = "Error while searching contact";
    public static final String SIGN_UP_ERROR = "Error while sign up";
    public static final String SIGN_IN_ERROR = "Error while sign in";
    public static final String REQUEST_ERROR = "Error in request:: {}";
    public static final String USERNAME_PASSWORD_INCORRECT = "Username or password incorrect";

    public static final String APPLICATION_EVENT_METHOD = "In On Application Event method";
    public static final String APPLICATION_EVENT_ERROR = "Exception In On Application Event Service - ";
    public static final String DEFAULT_ROLES_CREATE = "In Create default Roles method";
    public static final String DEFAULT_PRIVILEGES_CREATE = "In Create default Privileges method";
    public static final String DEFAULT_ROLES_CREATE_ERROR = "Exception In Create default Roles method - ";
    public static final String SUPER_ADMIN_CREATE = "In create super admin request";
    public static final String SUPER_ADMIN_CREATE_ERROR = "Exception In Create super admin method - ";

}
