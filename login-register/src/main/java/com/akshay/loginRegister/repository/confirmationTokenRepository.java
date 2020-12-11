package com.akshay.loginRegister.repository;

import com.akshay.loginRegister.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface confirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
        ConfirmationToken findByConfirmationToken(String confirmationToken);
}
