package com.hoangmn.web.controller;

import javax.servlet.http.HttpSession;

import com.hoangmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoangmn.model.User;

@Controller
public class LoginLogoutController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {

        return "login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(String username, String password,
                             HttpSession session) {

        User user = userService.getUser(username, password);
        if (user != null) {
            if (user.getRole().equals("user")) {
                session.setAttribute("user", user);
                return "redirect:/user";
            } else if (user.getRole().equals("admin")) {
                session.setAttribute("user", user);
                return "redirect:/admin";
            }
        }
        return "redirect:/login";

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/login";

    }
}
