package com.example.AppPfe.service;

import com.example.AppPfe.Models.emailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {
   @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          emailData body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("najjaramal220@gmail.com");
        message.setTo(toEmail);
        message.setText("code: "+body.getCode()+" date: "+body.getDate_()+" Materiels: "+body.getMateriel()+ " humaines: "+body.getHumain());
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail sent successfully...");
    }

    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        String attachment) throws MessagingException {

        MimeMessage mimeMessage=mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                =new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("najjaramal220@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem
                =new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        mailSender.send(mimeMessage);
        System.out.println("Mail send...");
    }
}
