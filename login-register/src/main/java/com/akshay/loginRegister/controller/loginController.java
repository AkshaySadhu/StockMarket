package com.akshay.loginRegister.controller;

import com.akshay.loginRegister.DTO.loginDto;
import com.akshay.loginRegister.serviceLayer.emailService;
import com.akshay.loginRegister.serviceLayer.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class loginController {

    @Autowired
    private loginService loginService;

    @Autowired
    private emailService emailService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody loginDto logindto){
        Map<String, String> responseMap = new HashMap<>();
        ResponseEntity<Map<String, String>> response = null;
        if(loginService.registerUser(logindto)) {
            String confirmationToken = emailService.generateConfirmationToken(logindto);
            try {
                if (confirmationToken != null) {
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setTo(logindto.getEmail());
                    mailMessage.setSubject("Complete Registration!");
                    mailMessage.setFrom("minisadhu@gmail.com");
                    mailMessage.setText("To confirm your account, please click here : "
                            + "http://localhost:8080/confirm-account?token=" + confirmationToken);

                    emailService.sendEmail(mailMessage);
                    responseMap.put("status", "Successfully registered. Please confirm your email");
                    response = new ResponseEntity<>(responseMap, HttpStatus.OK);
                }
            } catch (Exception e) {
                emailService.deleteConfirmationToken(confirmationToken);
                loginService.deleteUser(logindto);
                responseMap.put("status", "This User may already exits. Try with different credentials");
                response = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
            }
        }else{
            responseMap.put("status", "Registration unsuccessful. Username, email or phone number already exits. Please check your details");
            response = new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody loginDto loginDto){
        boolean status = loginService.login(loginDto);
        Map<String, String> responseMap = new HashMap<>();
        ResponseEntity<Map<String, String>> response;
        if(status){
            responseMap.put("status", "Login Successful");
            response = new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        else{
            responseMap.put("status", "Login Unsuccessful. Try again!!!");
            response = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/confirm-account")
    public ResponseEntity<Map<String, String>> verifyEmail(@RequestParam("token") String confirmationToken){
        boolean status = emailService.verifyEmail(confirmationToken);
        Map<String, String> responseMap = new HashMap<>();
        ResponseEntity<Map<String, String>> response;
        if(status == true){
            responseMap.put("status", "Email has been successfully verified");
            response = new ResponseEntity<>(responseMap, HttpStatus.OK);
        }else {
            responseMap.put("status", "Email verification unsuccessful");
            response = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile/{username}")
    public ResponseEntity<loginDto> getUserDetails(@PathVariable("username") String username){
        loginDto loginDto = loginService.getUserDetails(username);
        if(loginDto != null) {
            return new ResponseEntity<>(loginDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(loginDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/profile/update")
    public ResponseEntity<Map<String, String>> updateDataProfile(@RequestBody loginDto loginDto) {
        boolean updateStatus = loginService.updateDataProfile(loginDto);
        Map<String, String> resultMap = new HashMap<>();
        ResponseEntity<Map<String, String>> response;
        if(updateStatus){
            resultMap.put("status", "Updation of profile successful");
            response = new ResponseEntity<>(resultMap, HttpStatus.OK);
        }else{
            resultMap.put("status", "Updation of profile Unsuccessful");
            response = new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
