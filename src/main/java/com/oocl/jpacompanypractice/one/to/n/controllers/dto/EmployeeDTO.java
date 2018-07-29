package com.oocl.jpacompanypractice.one.to.n.controllers.dto;

import com.oocl.jpacompanypractice.one.to.n.entities.Employee;

public class EmployeeDTO {
    private final Long id;
    private final String name;
    private final Long companyId;

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getCompanyId() { return companyId; }

    public EmployeeDTO(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.companyId = employee.getCompany().getId();
    }
}
