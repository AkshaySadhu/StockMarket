package com.akshay.loginRegister.repository;

import com.akshay.loginRegister.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loginRepository extends CrudRepository<Login, Long> {

    Login findByUsername(String username);
    Login findByEmailIgnoreCase(String email);
}
