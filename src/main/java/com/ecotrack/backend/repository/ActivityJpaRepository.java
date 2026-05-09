package com.ecotrack.backend.repository;

import com.ecotrack.backend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityJpaRepository extends JpaRepository<Activity, Long> {

    // Fetches all activities belonging to a specific user by their email
    List<Activity> findByUserEmail(String userEmail);
}
