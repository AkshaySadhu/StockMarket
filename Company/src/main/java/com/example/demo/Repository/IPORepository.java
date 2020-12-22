package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.IPO;
@Repository
public interface IPORepository extends CrudRepository<IPO, Long> {

	IPO findByCompanyName(String username);

	Iterable<IPO> deleteByCompanyName(String companyname);

}

