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
            user.setId(rs.getInt("l_id"));
            user.setUserId(rs.getString("l_user_id"));
            user.setUserPassword(rs.getString("l_user_password"));
            user.setUserRole(rs.getString("u_role"));
            UserRole userRole = new UserRole();
            userRole.setUserId(rs.getString("u_id"));
            userRole.setUserRole(rs.getString("u_role"));

            return user;
        }
    }

    public User findByUsername(String username) {
        String sql = """
            SELECT 
                login_user.id AS l_id,
                login_user.user_id AS l_user_id,
                login_user.user_password AS l_user_password,
                user_role.user_id AS u_id,
                user_role.user_role AS u_role
            FROM login_user
                LEFT OUTER JOIN user_role
            ON login_user.user_id = user_role.user_id
            WHERE
                login_user.user_id = ?
        """;

        return jdbcTemplate.query(sql, new CustomRowMapper() , username).stream().findFirst().orElse(null);
    }

    public void insert(String userId, String userPassword, String userRole) {
        String user_sql = "INSERT INTO login_user(user_id, user_password) VALUES (?, ?)";
        String role_sql = "INSERT INTO user_role(user_id, user_role) VALUES (?, ?)";
        jdbcTemplate.update(user_sql, userId , userPassword);
        jdbcTemplate.update(role_sql, userId, userRole);
    }
}