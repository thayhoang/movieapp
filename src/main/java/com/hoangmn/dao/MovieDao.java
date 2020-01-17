package com.hoangmn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hoangmn.model.Movie;

@Repository
public class MovieDao {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Movie> getMovies() {
        String sql = "select * from movies";
        List<Movie> movies = jdbc.query(sql, getRowMapper());
        return movies;
    }

    private RowMapper<Movie> getRowMapper() {
        return new RowMapper<Movie>() {
            @Override
            public Movie mapRow(ResultSet rs, int arg1) throws SQLException {
                return new Movie(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }
        };
    }

    public Movie getMovie(int id) {
        String sql = "select * from movies where id = ?";
        List<Movie> movies = jdbc.query(sql, getRowMapper(), id);
        if (movies.size() > 0) {
            return movies.get(0);
        }
        return null;
    }

    public int save(Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                String sql = "insert into movies(title,description,trailer) values(?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql,
                        new String[] { "id" });
                ps.setString(1, movie.getTitle());
                ps.setString(2, movie.getDescription());
                ps.setString(3, movie.getTrailer());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int delete(int id) {
        String sql = "delete from movies where id = ?";
        return jdbc.update(sql, id);
    }

    public int update(Movie movie) {
        String sql = "update movies set title = ? , description = ? , trailer = ? where id = ?";
        return jdbc.update(sql, movie.getTitle(), movie.getDescription(), movie.getTrailer(), movie.getId());
    }



}
