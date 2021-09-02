package com.example.demo.utils;

import lombok.Getter;

import static com.example.demo.utils.Constants.USER_UNIQUE_EMAIL_CONSTRAINT;
import static com.example.demo.utils.Constants.USER_UNIQUE_USERNAME_CONSTRAINT;

@Getter
public enum UserUniqueConstraints {
    USERNAME(USER_UNIQUE_USERNAME_CONSTRAINT), EMAIL(USER_UNIQUE_EMAIL_CONSTRAINT);

    private final String constraintName;

    UserUniqueConstraints(String constraintName) {
        this.constraintName = constraintName;
    }
}
