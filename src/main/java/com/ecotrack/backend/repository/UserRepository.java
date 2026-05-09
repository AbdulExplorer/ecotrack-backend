package com.ecotrack.backend.repository;

import com.ecotrack.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    /**
     * Saves a new user to the database.
     * Returns 1 on success (mirrors the old JdbcTemplate.update() return value).
     */
    public int saveUser(String name, String email, String state, String city, String password) {
        User user = new User(name, email, state, city, password);
        userJpaRepository.save(user);
        return 1;
    }

    /**
     * Validates credentials and returns the user's data as a Map.
     * Keys match the original DB column names so the frontend response is unchanged.
     */
    public Map<String, Object> login(String email, String password) {
        User user = userJpaRepository
                .findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Build map with the same column-name keys the old JDBC queryForMap() returned
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id",       user.getId());
        result.put("name",     user.getName());
        result.put("email",    user.getEmail());
        result.put("state",    user.getState());
        result.put("city",     user.getCity());
        result.put("password", user.getPassword());
        return result;
    }
}
