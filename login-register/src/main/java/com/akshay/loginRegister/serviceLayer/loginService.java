package com.akshay.loginRegister.serviceLayer;

import com.akshay.loginRegister.DTO.loginDto;
import com.akshay.loginRegister.model.*;
import com.akshay.loginRegister.repository.loginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class loginService {

    @Autowired
    private loginRepository loginRepository;

    public boolean registerUser(loginDto loginDto) {
        Login login = new Login();
        BeanUtils.copyProperties(loginDto, login);
        Login usernameExists = loginRepository.findByUsername(login.getUsername());
        Login emailExists = loginRepository.findByEmailIgnoreCase(login.getEmail());
        Login phnoExists = loginRepository.findByPhoneNumber(login.getPhoneNumber());
        if((usernameExists == null) && (emailExists == null) && (phnoExists == null)) {
            loginRepository.save(login);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean login(loginDto loginDto) {
        Login login = new Login();
        BeanUtils.copyProperties(loginDto, login);
        String username = login.getUsername();
        Login loginUser = loginRepository.findByUsername(username);
        if (login.getPassword().equals(loginUser.getPassword()) && login.getUserType().equals(loginUser.getUserType()) && loginUser.getConfirmed() == true) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteUser(loginDto loginDto){
        Login login = new Login();
        BeanUtils.copyProperties(loginDto, login);
        loginRepository.delete(login);
    }

    public loginDto getUserDetails(String username) {
        Login login = loginRepository.findByUsername(username);
        if(login != null) {
            loginDto loginDto = null;
            BeanUtils.copyProperties(login, loginDto);
            return loginDto;
        }else{
            return null;
        }
    }

    public boolean updateDataProfile(loginDto loginDto) {
        Login login = loginRepository.findByUsername(loginDto.getUsername());
        if(login != null) {
            if (StringUtils.isNotBlank(loginDto.getPassword())) {
                login.setPassword(loginDto.getPassword());
            }
            if (StringUtils.isNotBlank(loginDto.getEmail())) {
                login.setEmail(loginDto.getEmail());
            }
            if (StringUtils.isNotBlank(loginDto.getPhoneNumber())) {
                login.setPhoneNumber(loginDto.getPhoneNumber());
            }
            return true;
        }else{
            return false;
        }
    }
}
