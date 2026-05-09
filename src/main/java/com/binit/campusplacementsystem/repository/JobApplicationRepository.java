package com.binit.campusplacementsystem.repository;

import com.binit.campusplacementsystem.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

}