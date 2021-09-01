package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    // Messages
    public static final String ENTITY_NOT_FOUND_MSG = "Entity with ID: %s not found.";
    public static final String ID_DOES_NOT_EXIST = "ID: %s does NOT exist.";
    public static final String VALIDATION_MESSAGE_PREFIX = "Error occurred: ";
    public static final String ALREADY_EXISTS_MESSAGE = "%s already exists.";

    // Defaults
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    // Constraint names
    public static final String USER_UNIQUE_USERNAME_CONSTRAINT = "uc_users_username";
    public static final String USER_UNIQUE_EMAIL_CONSTRAINT = "uc_users_email";
    public static final String SHOP_UNIQUE_NAME_CONSTRAINT = "uc_shop_name";
    public static final String REVIEW_UNIQUE_USER_ID_CONSTRAINT = "uc_user_id_reviews";
}
