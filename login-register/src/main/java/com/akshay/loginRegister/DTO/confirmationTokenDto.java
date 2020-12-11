package com.akshay.loginRegister.DTO;

import com.akshay.loginRegister.model.Login;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class confirmationTokenDto {
    private long tokenid;

    private String confirmationToken;

    private Date createdDate;

    private Login login;

    public confirmationTokenDto() {}

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
