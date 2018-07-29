package com.oocl.jpacompanypractice.one.to.n.repositories;

import com.oocl.jpacompanypractice.one.to.n.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByName(String name);
    @Transactional
    int deleteCompanyByName(@Param("name") String name);
    boolean existsByName(String name);
}
