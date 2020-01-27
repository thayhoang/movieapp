package com.hoangmn.service;

import com.hoangmn.dao.UserDao;
import com.hoangmn.model.Movie;
import com.hoangmn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<Movie> getFavs(User user) {
        return userDao.getFavs(user.getId());
    }

    public List<Movie> getNonFavs(User user) {
        return userDao.getNonFavs(user.getId());
    }

    public boolean isFavorite(int userId, Integer id) {
        return userDao.isFavorite(userId, id);
    }

    public int removeFromFavorites(int userId, int movieId) {
        return userDao.removeFromFavorites(userId, movieId);
    }

    public int addToFavorites(int userId, int movieId) {
        return userDao.addToFavorites(userId, movieId);
    }

    public User getUser(String username, String password) {
        return userDao.getUser(username, password);
    }
}
