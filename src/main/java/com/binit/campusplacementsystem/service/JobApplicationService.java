package com.binit.campusplacementsystem.service;

import com.binit.campusplacementsystem.model.Job;
import com.binit.campusplacementsystem.model.JobApplication;
import com.binit.campusplacementsystem.model.Student;
import com.binit.campusplacementsystem.repository.JobApplicationRepository;
import com.binit.campusplacementsystem.repository.JobRepository;
import com.binit.campusplacementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 StudentRepository studentRepository,
                                 JobRepository jobRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
    }

    public JobApplication applyForJob(int studentId, int jobId, JobApplication jobApplication) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Job job = jobRepository.findById(jobId).orElse(null);

        if (student == null || job == null) {
            return null;
        }

        jobApplication.setStudent(student);
        jobApplication.setJob(job);
        jobApplication.setStatus("APPLIED");
        jobApplication.setAppliedDate(LocalDate.now());

        return jobApplicationRepository.save(jobApplication);
    }

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication getApplicationById(int id) {
        return jobApplicationRepository.findById(id).orElse(null);
    }

    public JobApplication updateApplicationStatus(int id, String status) {
        JobApplication application = jobApplicationRepository.findById(id).orElse(null);

        if (application == null) {
            return null;
        }

        application.setStatus(status);
        return jobApplicationRepository.save(application);
    }

    public String deleteApplication(int id) {
        jobApplicationRepository.deleteById(id);
        return "Application deleted successfully";
    }
}