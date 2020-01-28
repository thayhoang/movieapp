package com.hoangmn.controller;

import javax.servlet.http.HttpSession;

import com.hoangmn.service.MovieService;
import com.hoangmn.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoangmn.model.User;

@Controller
public class AuthenticationController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(String username, String password, HttpSession session) {
        User user = movieService.getUser(username, password);
        if (Util.isValidUser(user)) {
            session.setAttribute("user", user);
        }
        String requestURI = (String) session.getAttribute("requestURI");
        if (requestURI != null && !requestURI.isEmpty() && !requestURI.equals("/")) {
            session.removeAttribute("requestURI");
            return "redirect:" + requestURI;
        } else if (Util.isUser(user)) {
            return "redirect:/app";
        } else if (Util.isAdmin(user)) {
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
