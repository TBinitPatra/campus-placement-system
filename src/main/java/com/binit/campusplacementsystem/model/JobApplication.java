package com.binit.campusplacementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class JobApplication {

    @Id
    private int id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Job job;

    private String status;

    private LocalDate appliedDate;

    public JobApplication() {
    }

    public JobApplication(int id, Student student, Job job, String status, LocalDate appliedDate) {
        this.id = id;
        this.student = student;
        this.job = job;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }
}