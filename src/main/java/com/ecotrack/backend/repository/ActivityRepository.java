package com.ecotrack.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveActivity(String email, String category, String subtype,
                            double value, String date, String note, double co2) {

        String sql = "INSERT INTO activities (user_email, category, subtype, value, date, note, co2) VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, email, category, subtype, value, date, note, co2);
    }
}