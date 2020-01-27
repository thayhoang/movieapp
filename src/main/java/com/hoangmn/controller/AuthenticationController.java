package com.hoangmn.controller;

import javax.servlet.http.HttpSession;

import com.hoangmn.util.Util;
import com.hoangmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoangmn.model.User;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(String username, String password, HttpSession session) {
        User user = userService.getUser(username, password);
        if (Util.isUser(user)) {
            session.setAttribute("user", user);
            return "redirect:/app";
        } else if (Util.isAdmin(user)) {
            session.setAttribute("user", user);
            return "redirect:/admin/movie";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
