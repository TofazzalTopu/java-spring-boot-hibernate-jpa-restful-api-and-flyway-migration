package com.user.progress.constant;

import org.springframework.stereotype.Component;

/**
 * @author Tofazzal
 */

@Component
public class AppConstants {
    public static final String API_VERSION = "v1";

    public static final String USER_SAVED_SUCCESS = "User saved successfully.";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_NAME_ALREADY_EXIST = "User already exist with the username: ";
    public static final String USER_FETCH_SUCCESS = "User fetch successfully.";

    public static final String USER_PROGRESS_SAVED_SUCCESS = "User progress saved successfully.";
    public static final String USER_PROGRESS_UPDATED_SUCCESS = "User progress updated successfully.";
    public static final String USER_PROGRESS_NOT_FOUND = "User progress not found.";
    public static final String USER_PROGRESS_ALREADY_EXIST_WITH_THIS_USER = "User progress already exist with the user id.";
    public static final String USER_PROGRESS_FETCH_SUCCESS = "User progress fetch successfully.";
}
