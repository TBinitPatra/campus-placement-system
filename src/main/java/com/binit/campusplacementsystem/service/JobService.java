package com.binit.campusplacementsystem.service;

import com.binit.campusplacementsystem.model.Company;
import com.binit.campusplacementsystem.model.Job;
import com.binit.campusplacementsystem.repository.CompanyRepository;
import com.binit.campusplacementsystem.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public Job addJob(int companyId, Job job) {
        Company company = companyRepository.findById(companyId).orElse(null);

        if (company == null) {
            return null;
        }

        if (!company.getStatus().equals("APPROVED")) {
            return null;
        }

        job.setCompany(company);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    public String deleteJob(int id) {
        jobRepository.deleteById(id);
        return "Job deleted successfully";
    }
}