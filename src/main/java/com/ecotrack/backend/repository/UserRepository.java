package com.ecotrack.backend.repository;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveUser(String name, String email, String state, String city, String password) {
        String sql = "INSERT INTO users (name, email, state, city, password) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, name, email, state, city, password);
    }
    
    public Map<String, Object> login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        return jdbcTemplate.queryForMap(sql, email, password);
    }
}

