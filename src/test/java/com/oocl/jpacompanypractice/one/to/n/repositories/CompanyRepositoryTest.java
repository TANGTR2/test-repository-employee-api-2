package com.oocl.jpacompanypractice.one.to.n.repositories;

import com.oocl.jpacompanypractice.one.to.n.entities.Company;
import com.oocl.jpacompanypractice.one.to.n.entities.Employee;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TestEntityManager entityManager;

    @After
    public void tearDown() throws Exception{
        entityManager.clear();
    }

    @Test
    public void findAll() {
        //given
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("alibaba1","male");
        employees.add(employee);
        entityManager.persist(new Company("alibaba",employees));
        entityManager.persist(new Company("tengxun"));
        //when
        List<Company> companyList = companyRepository.findAll();
        //then
        assertThat(companyList.size(), is(2));
        assertThat(companyList.get(0).getName(), is("alibaba"));
        assertThat(companyList.get(1).getName(), is("tengxun"));
    }

    @Test
    public void findByName() {
        //given
        String name = "tengxun";
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("tengxun1","male");
        employees.add(employee);
        entityManager.persist(new Company("tengxun",employees));
        //when
        Company company = companyRepository.findByName(name);
        //then
        assertThat(company.getName(), is("tengxun"));
    }

    @Test
    public void deleteCompanyByName() {
    }

    @Test
    public void existsByName() {
    }
}
