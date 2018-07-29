package com.oocl.jpacompanypractice.one.to.n.repositories;

import com.oocl.jpacompanypractice.one.to.n.entities.Employee;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @After
    public void tearDown() throws Exception{
        entityManager.clear();
    }

    @Test
    public void findByGender() {
        //given
        entityManager.persist(new Employee("alibaba1","male"));
        entityManager.persist(new Employee("tengxun1","male"));
        //when
        List<Employee> employees = employeeRepository.findByGender("male");
        //then
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getName(), is("alibaba1"));
        assertThat(employees.get(0).getGender(),is("male"));
        assertThat(employees.get(1).getName(), is("tengxun1"));
        assertThat(employees.get(1).getGender(),is("male"));
    }

    @Test
    public void deleteEmployeeById() {
        //given
        entityManager.clear();
        entityManager.persist(new Employee("alibaba1","male"));
        //when
        System.out.println(employeeRepository.findAll().size());
        Long id = Long.valueOf(entityManager.persistAndGetId(new Employee("tengxun1","male")).toString());
        int isDeleted = employeeRepository.deleteEmployeeById(id);
        //then
        assertThat(isDeleted, is(1));
        assertThat(employeeRepository.findAll().size(), is(1));
    }
}