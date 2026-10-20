package com.example.gakuboschedules.repository;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.gakuboschedules.entity.User;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByEmail(String email) {
        String sql = "SELECT id, user_email, user_password, user_name FROM login_user WHERE user_email = ?";
        return jdbcTemplate.query(sql, rs -> {
            if(!rs.next()) return null;
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            user.setUserName(rs.getString("user_name"));

            return user;
        }, email);
    }

    public User findByUsername(String username) {
        String sql = "SELECT id, user_email, user_password, user_name FROM login_user WHERE user_username = ?";
        return jdbcTemplate.query(sql, rs -> {
            if(!rs.next()) return null;
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            user.setUserName(rs.getString("user_name"));

            return user;
        }, username);
    }

    public void insert(User user) {
        String sql = "INSERT INTO login_user(user_email, user_password, user_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserEmail(), user.getUserPassword(), user.getUserName());
    }
}