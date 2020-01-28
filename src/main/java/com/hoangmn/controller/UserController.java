package com.hoangmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hoangmn.service.MovieService;
import com.hoangmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hoangmn.model.Movie;
import com.hoangmn.model.User;

@Controller
@RequestMapping(value = "/app")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showMovies(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Movie> favs = userService.getFavs(user);
        List<Movie> nonFavs = userService.getNonFavs(user);
        model.addAttribute("favs", favs);
        model.addAttribute("nonfavs", nonFavs);
        return "user-movie-list";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String showMovie(Model model, HttpSession session, @PathVariable int id) {
        Movie movie = movieService.getMovie(id);
        User user = (User) session.getAttribute("user");
        boolean isFav = userService.isFavorite(user.getId(), id);
        List<Movie> favs = userService.getFavs(user);
        model.addAttribute("favs", favs);
        model.addAttribute("movie", movie);
        model.addAttribute("isfav", isFav);
        return "user-movie";
    }

    @RequestMapping(value = "/movie/{id}/nonfav", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String removeFromFavorites(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        return userService.removeFromFavorites(userId, id) > 0 ? "OK" : "Error";
    }

    @RequestMapping(value = "/movie/{id}/fav", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String addToFavorites(HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        return userService.addToFavorites(userId, id) > 0 ? "OK" : "Error";
    }

}
