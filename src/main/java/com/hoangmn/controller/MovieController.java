package com.hoangmn.controller;

import javax.servlet.http.HttpSession;

import com.hoangmn.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hoangmn.model.User;

@Controller
@RequestMapping(value = "/app")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showMovies(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("favs", movieService.getFavs(user));
        model.addAttribute("nonfavs",  movieService.getNonFavs(user));
        return "user-movie-list";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String showMovie(Model model, HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("favs", movieService.getFavs(user));
        model.addAttribute("movie", movieService.getMovie(id));
        model.addAttribute("isfav", movieService.isFavorite(user, id));
        return "user-movie";
    }

    @RequestMapping(value = "/movie/{id}/nonfav", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String removeFromFavorites(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return movieService.removeFromFavorites(user, id) > 0 ? "OK" : "Error";
    }

    @RequestMapping(value = "/movie/{id}/fav", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String addToFavorites(HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");;
        return movieService.addToFavorites(user, id) > 0 ? "OK" : "Error";
    }

}
