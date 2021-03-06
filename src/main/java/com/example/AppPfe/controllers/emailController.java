package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.mailBody;
import com.example.AppPfe.Models.mailBodyEcart;
import com.example.AppPfe.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mail")
public class emailController {
    @Autowired
    private EmailSenderService sender;

    @PostMapping()
    public boolean send(@RequestBody mailBody mailBody){
        System.out.println(mailBody);
        this.sender.sendEmail(mailBody.getToEmail(),"Validation demande",mailBody.getBody());

        return true;
    }

    @PostMapping("/ecart")
    public boolean sendEcart(@RequestBody mailBodyEcart mailBodyEcart){
        System.out.println(mailBodyEcart);
        this.sender.sendEmailEcart(mailBodyEcart.getToEmail(),"Notification Ecart","Un écart est constaté");

        return true;
    }

}
