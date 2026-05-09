package com.binit.campusplacementsystem.controller;

import com.binit.campusplacementsystem.model.Company;
import com.binit.campusplacementsystem.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable int id) {
        return companyService.deleteCompany(id);
    }

    @PutMapping("/{id}/approve")
    public Company approveCompany(@PathVariable int id) {
        return companyService.approveCompany(id);
    }

    @PutMapping("/{id}/reject")
    public Company rejectCompany(@PathVariable int id) {
        return companyService.rejectCompany(id);
    }
}