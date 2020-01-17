package com.hoangmn.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hoangmn.service.MovieService;
import com.hoangmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hoangmn.model.Movie;
import com.hoangmn.model.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "")
    public String showUserHomePage() {
        return "user-home-page";
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String showMovies(Model model, HttpSession session, Integer id) {

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<Movie> favs = userService.getFavs(userId);

        if (id == null) {

            List<Movie> nonFavs = userService.getNonFavs(userId);
            model.addAttribute("favs", favs);
            model.addAttribute("nonfavs", nonFavs);
            return "user-movie-list";

        } else {

            Movie movie = movieService.getMovie(id);

            if (movie != null) {

                boolean isFav = userService.isFavorite(userId, id);
                model.addAttribute("favs", favs);
                model.addAttribute("movie", movie);
                model.addAttribute("isfav", isFav);
                return "user-movie-single";

            }
            return "/redirect:/user/movies";

        }
    }

    @RequestMapping(value = "/movies/remove", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String removeFromFavorites(int movieId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        return userService.removeFromFavorites(userId, movieId) > 0 ? "OK"
                : "This action cannot be performed at this time";
    }

    @RequestMapping(value = "/movies/add", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String addToFavorites(int movieId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        return userService.addToFavorites(userId, movieId) > 0 ? "OK"
                : "This action cannot be performed at this time";
    }

    @SuppressWarnings("unused")
    private void setNoCache(HttpServletResponse response) {
        response.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies. return
    }
}
