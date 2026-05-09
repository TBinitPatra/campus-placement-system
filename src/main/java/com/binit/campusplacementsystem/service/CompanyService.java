package com.binit.campusplacementsystem.service;

import com.binit.campusplacementsystem.model.Company;
import com.binit.campusplacementsystem.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addCompany(Company company) {
        company.setStatus("PENDING");
        return companyRepository.save(company);
    }

    public Company approveCompany(int id) {
        Company company = companyRepository.findById(id).orElse(null);

        if (company == null) {
            return null;
        }

        company.setStatus("APPROVED");
        return companyRepository.save(company);
    }

    public Company rejectCompany(int id) {
        Company company = companyRepository.findById(id).orElse(null);

        if (company == null) {
            return null;
        }

        company.setStatus("REJECTED");
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company updateCompany(int id, Company newCompany) {
        Company existingCompany = companyRepository.findById(id).orElse(null);

        if (existingCompany == null) {
            return null;
        }

        existingCompany.setName(newCompany.getName());
        existingCompany.setEmail(newCompany.getEmail());
        existingCompany.setLocation(newCompany.getLocation());
        existingCompany.setIndustry(newCompany.getIndustry());
        existingCompany.setStatus(newCompany.getStatus());

        return companyRepository.save(existingCompany);
    }

    public String deleteCompany(int id) {
        companyRepository.deleteById(id);
        return "Company deleted successfully";
    }
}