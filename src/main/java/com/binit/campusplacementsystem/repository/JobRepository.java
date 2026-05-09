package com.binit.campusplacementsystem.repository;

import com.binit.campusplacementsystem.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

}