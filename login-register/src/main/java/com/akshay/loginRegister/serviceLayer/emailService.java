package com.akshay.loginRegister.serviceLayer;

import com.akshay.loginRegister.DTO.loginDto;
import com.akshay.loginRegister.repository.*;
import com.akshay.loginRegister.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class emailService {

    private JavaMailSender javaMailSender;

    @Autowired
    private confirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private loginRepository loginRepository;

    @Autowired
    public emailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public String generateConfirmationToken(loginDto loginDto) {
        Login login = new Login();
        BeanUtils.copyProperties(loginDto, login);
        System.out.println("Confirmed: " + login.getConfirmed() + " Email: " + login.getEmail());
        Login userExists = loginRepository.findByEmailIgnoreCase(login.getEmail());
        if (userExists != null) {
            ConfirmationToken confirmationToken = new ConfirmationToken(userExists);
            confirmationTokenRepository.save(confirmationToken);
            return confirmationToken.getConfirmationToken();
        } else {
            return null;
        }
    }

    public boolean verifyEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            Login login = token.getLogin();
            login.setConfirmed(true);
            loginRepository.save(login);
            return true;
        } else {
            return false;
        }
    }

    public void deleteConfirmationToken(String confirmationToken) {
        ConfirmationToken deleteConfirmationToken = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        confirmationTokenRepository.delete(deleteConfirmationToken);
    }
}
