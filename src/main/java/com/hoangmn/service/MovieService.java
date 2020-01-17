package com.hoangmn.service;

import com.hoangmn.dao.MovieDao;
import com.hoangmn.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    public Movie getMovie(Integer id) {
        return movieDao.getMovie(id);
    }
}
