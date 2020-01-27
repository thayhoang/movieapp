package com.hoangmn.util;

import com.hoangmn.model.User;

public class Util {

    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN = "admin";

    public static boolean isAdmin(User user) {
        if (user != null && user.getRole() != null && user.getRole().equals(ROLE_ADMIN)) {
           return true;
        }
        return false;
    }

    public static boolean isUser(User user) {
        if (user != null && user.getRole() != null && user.getRole().equals(ROLE_USER)) {
            return true;
        }
        return false;
    }

}
