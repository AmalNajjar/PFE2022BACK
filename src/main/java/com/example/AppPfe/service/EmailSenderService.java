
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
        message.setFrom("chaymagharbi615@gmail.com");
        message.setTo(toEmail);
        message.setText("Code de plan d'enlèvement: "+body.getCode()+"\n"+"Date d'enlèvement: "+body.getDate_()+"\n"+"Moyens Materiels: "+body.getMateriel()+"\n"+"Moyens humains: "+body.getHumain());
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail sent successfully...");
    }
    public void sendEmailEcart(String toEmail,
                               String subject,
                               String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("najjaramal220@gmail.com");
        message.setTo(toEmail);
        message.setText("Ecart egale 0");
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

        mimeMessageHelper.setFrom("chaymagharbi615@gmail.com");
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