package com.hoangmn.service;

import com.hoangmn.mapper.MovieMapper;
import com.hoangmn.model.Movie;
import com.hoangmn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    public Movie getMovie(int id) {
        return movieMapper.get(id);
    }

    public List<Movie> getAll() {
        return movieMapper.getAll();
    }

    public int save(Movie movie) {
        return movieMapper.save(movie);
    }

    public int delete(int id) {
        return movieMapper.delete(id);
    }

    public List<Movie> getFavs(User user) {
        return movieMapper.getFavs(user.getId());
    }

    public List<Movie> getNonFavs(User user) {
        return movieMapper.getNonFavs(user.getId());
    }

    public boolean isFavorite(User user, int movieId) {
        return movieMapper.getCountFromFav(user.getId(), movieId) > 0;
    }

    public int removeFromFavorites(User user, int movieId) {
        return movieMapper.removeFromFavorites(user.getId(), movieId);
    }

    public int addToFavorites(User user, int movieId) {
        return movieMapper.addToFavorites(user.getId(), movieId);
    }

    public User getUser(String username, String password) {
        return movieMapper.getUser(username, password);
    }

    public int update(Movie movie) {
        return movieMapper.update(movie);
    }
}
