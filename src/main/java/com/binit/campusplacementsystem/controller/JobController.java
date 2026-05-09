package com.binit.campusplacementsystem.controller;

import com.binit.campusplacementsystem.model.Job;
import com.binit.campusplacementsystem.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/company/{companyId}")
    public Job addJob(@PathVariable int companyId, @RequestBody Job job) {
        return jobService.addJob(companyId, job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable int id) {
        return jobService.deleteJob(id);
    }
}