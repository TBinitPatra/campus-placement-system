package com.binit.campusplacementsystem.repository;

import com.binit.campusplacementsystem.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}