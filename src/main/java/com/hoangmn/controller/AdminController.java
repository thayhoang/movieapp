package com.hoangmn.controller;

import com.hoangmn.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hoangmn.model.Movie;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public String showMovies(Model model) {
		model.addAttribute("movies", movieService.getAll());
		return "admin-movies";
	}

	@RequestMapping(value = "/movie/add", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String addMovie(Movie movie) {
		movieService.save(movie);
		return movie.getId() > 0 ? "" + movie.getId() : "Error";
	}

	@RequestMapping(value = "/movie/delete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteMovie(int id) {
		return movieService.delete(id) > 0 ? "OK" : "Error";
	}

	@RequestMapping(value = "/movie/update", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateMovie(Movie movie) {
		System.out.printf("");
		return movieService.update(movie) > 0 ? "OK" : "Error";
	}


}
