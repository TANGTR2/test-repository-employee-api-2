package com.oocl.jpacompanypractice.one.to.n.repositories;

import com.oocl.jpacompanypractice.one.to.n.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByGender(String gender);
}
