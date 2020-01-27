package com.hoangmn.controller;

import java.util.List;
import com.hoangmn.dao.MovieDao;
import com.hoangmn.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hoangmn.model.Movie;
import com.hoangmn.model.User;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public String showMovies(Model model) {
		List<Movie> movies = movieDao.getMovies();
		model.addAttribute("movies", movies);
		return "admin-movies";
	}

	@RequestMapping(value = "/movie/add", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String addMovie(Movie movie) {
		int key = movieDao.save(movie);
		return key > 0 ? "" + key : "Error";
	}

	@RequestMapping(value = "/movie/delete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteMovie(int id) {
		return movieDao.delete(id) > 0 ? "OK" : "Error";
	}

/*	@RequestMapping(value = "/managemovies/update", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateMovie(String fieldName, Movie movie) {
		return movieDAO.update(fieldName, movie) > 0 ? "OK"
				: "This action cannot be performed at this time";
	}
*/
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String showUsers(Model model) {
		List<User> users = userDao.getUsers();
		model.addAttribute("users", users);
		return "admin-users";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String addUser(User user) {
		int key = userDao.save(user);
		return key > 0 ? "" + key : "Error";
	}

	@RequestMapping(value = "/manageusers/delete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteUser(int id) {
		return userDao.delete(id) > 0 ? "OK" : "Error";
	}

	/*@RequestMapping(value = "/manageusers/update", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateUser(String fieldName, User user) {
		return userDAO.update(fieldName, user) > 0 ? "OK"
				: "This action cannot be performed at this time";
	}
*/

}
