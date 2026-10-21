package com.example.gakuboschedules.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.gakuboschedules.entity.User;
import com.example.gakuboschedules.entity.UserRole;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static class CustomRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserId(rs.getString("user_id"));
            user.setUserPassword(rs.getString("user_password"));
            UserRole userRole = new UserRole();
            userRole.setUserId(rs.getString("user_id"));
            userRole.setUserRole(rs.getString("user_role"));

            return user;
        }
    }

    public User findByUsername(String username) {
        String sql = """
            SELECT 
                login_user.id AS l_id,
                login_user.user_id AS l_userid,
                login_user.user_password AS l_password,
                user_roles.user_id AS u_id,
                user_roles.user_role AS u_role
            FROM login_user
                LEFT OUTER JOIN user_roles
            ON login_user.id = user_roles.user_id
            WHERE
                login_user.id = ?
        """;

        return jdbcTemplate.queryForObject(sql, new CustomRowMapper(), username);
    }

    public void insert(User user) {
        String sql = "INSERT INTO login_user(user_id, user_password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUserId() , user.getUserPassword());
    }
}