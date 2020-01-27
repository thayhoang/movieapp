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
import com.hoangmn.model.User;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbc;

    public User getUser(String username, String password) {

        String sql = "select * from users where username = ? and password = ?";
        List<User> users = jdbc.query(sql, getUserRowMapper(), username, password);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public List<User> getUsers() {
        String sql = "select * from users";
        List<User> users = jdbc.query(sql, getUserRowMapper());
        return users;
    }

    public int update(User user) {
        String sql = "update users set username = ? ,password = ? ,role = ? ,fullName = ? ,dob= ? where id = ?";
        return jdbc
                .update(sql, user.getUsername(), user.getPassword(),
                        user.getRole(), user.getFullName(), user.getDob(),
                        user.getId());
    }

    public int save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                String sql = "insert into users (username,password,role,fullName,dob) values(?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql,
                        new String[] { "id" });
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.setString(4, user.getFullName());
                ps.setDate(5, new java.sql.Date(user.getDob().getTime()));
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();

    }

    public int delete(int id) {
        String sql = "delete from users where id = ?";
        return jdbc.update(sql, id);
    }

    public List<Movie> getFavs(int userId) {

        String sql = "select * from movies where id IN (select movieId from favorites where userId = ? )";
        return jdbc.query(sql, getMovieRowMapper(), userId);
    }

    public List<Movie> getNonFavs(int userId) {
        String sql = "select * from movies where id NOT IN (select movieId from favorites where userId = ? )";
        return jdbc.query(sql, getMovieRowMapper(), userId);
    }

    private RowMapper<Movie> getMovieRowMapper() {
        return new RowMapper<Movie>() {
            @Override
            public Movie mapRow(ResultSet rs, int arg1) throws SQLException {
                Movie movie = new Movie(rs.getInt(1), rs.getString(2), rs
                        .getString(3), rs.getString(4));
                return movie;
            }
        };
    }

    private RowMapper<User> getUserRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int arg1) throws SQLException {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(rs.getString(4));
                user.setFullName(rs.getString(5));
                return user;
            }
        };
    }

    public boolean isFavorite(int userId, int movieId) {

        String sql = "select count(*) from favorites where userId = ? and movieId = ?";
        int i = jdbc.queryForObject(sql, Integer.class, userId, movieId);
        return i == 1;

    }

    public int addToFavorites(int userId, int movieId) {
        String sql = "insert into favorites(userId,movieId) values (?,?)";
        return jdbc.update(sql, userId, movieId);

    }

    public int removeFromFavorites(int userId, int movieId) {
        String sql = "delete from favorites where userId =? and movieId = ?";
        return jdbc.update(sql, userId, movieId);
    }

}
