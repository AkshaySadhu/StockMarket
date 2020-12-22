package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Company findByCompanyName(String username);

    Company deleteByCompanyName(String companyname);


}



