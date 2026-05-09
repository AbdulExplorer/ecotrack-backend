package com.ecotrack.backend.repository;

import com.ecotrack.backend.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ActivityRepository {

    @Autowired
    private ActivityJpaRepository activityJpaRepository;

    /**
     * Saves a new activity.
     * Returns 1 on success (mirrors the old JdbcTemplate.update() return value).
     */
    public int saveActivity(String email, String category, String subtype,
                            double value, String date, String note, double co2) {
        Activity activity = new Activity(email, category, subtype, value, date, note, co2);
        activityJpaRepository.save(activity);
        return 1;
    }

    /**
     * Fetches all activities for a given user email.
     * Returns List<Map<String, Object>> with the same column-name keys
     * that the old JDBC queryForList() returned, keeping the frontend response unchanged.
     */
    public List<Map<String, Object>> getUserActivities(String email) {
        List<Activity> activities = activityJpaRepository.findByUserEmail(email);

        return activities.stream().map(a -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id",         a.getId());
            map.put("user_email", a.getUserEmail());
            map.put("category",   a.getCategory());
            map.put("subtype",    a.getSubtype());
            map.put("value",      a.getValue());
            map.put("date",       a.getDate());
            map.put("note",       a.getNote());
            map.put("co2",        a.getCo2());
            return map;
        }).collect(Collectors.toList());
    }
}
