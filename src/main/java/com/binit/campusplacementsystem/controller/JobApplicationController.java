package com.binit.campusplacementsystem.controller;

import com.binit.campusplacementsystem.model.JobApplication;
import com.binit.campusplacementsystem.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping("/student/{studentId}/job/{jobId}")
    public JobApplication applyForJob(@PathVariable int studentId,
                                      @PathVariable int jobId,
                                      @RequestBody JobApplication jobApplication) {
        return jobApplicationService.applyForJob(studentId, jobId, jobApplication);
    }

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public JobApplication getApplicationById(@PathVariable int id) {
        return jobApplicationService.getApplicationById(id);
    }

    @PutMapping("/{id}/status")
    public JobApplication updateApplicationStatus(@PathVariable int id,
                                                  @RequestParam String status) {
        return jobApplicationService.updateApplicationStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable int id) {
        return jobApplicationService.deleteApplication(id);
    }
}