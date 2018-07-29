package com.oocl.jpacompanypractice.one.to.n.controllers;

import com.oocl.jpacompanypractice.one.to.n.controllers.dto.CompanyDTO;
import com.oocl.jpacompanypractice.one.to.n.entities.Company;
import com.oocl.jpacompanypractice.one.to.n.repositories.CompanyRepository;
import com.oocl.jpacompanypractice.one.to.n.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @PostMapping(path="/v1/companies")
    public Company save(@RequestBody Company company){
        company.getEmployees().stream().forEach(employee -> {
            employee.setCompany(company);
        });
        return companyRepository.save(company);
    }

    @GetMapping(path="/v1/companies")
    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    @GetMapping(path = "/v1/companies/{id}")
    public CompanyDTO get(@PathVariable Long id) {
        Company company = companyRepository.findById(id).get();
        return new CompanyDTO(company);
    }

    @PutMapping(path = "/v1/companies/{id}")
    public ResponseEntity update(@RequestBody Company company) {
        company.getEmployees().stream().filter(employee -> employee.getCompany() == null).forEach(employee -> {
            employee.setCompany(company);
        });
        companyRepository.save(company);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/v1/companies/{id}")
    public Company delete(@PathVariable Long id) {
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
        return company;
    }
}
