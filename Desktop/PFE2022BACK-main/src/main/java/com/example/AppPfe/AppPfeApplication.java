package com.example.AppPfe;


import com.example.AppPfe.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class AppPfeApplication {
@Autowired
private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(AppPfeApplication.class, args);}

/*
@EventListener(ApplicationReadyEvent.class)
public void sendMail(){
	senderService.sendEmail("najjaramal220@gmail.com","this is Subject",
			"this is Body of Email");
}
public void sendMail() throws MessagingException {
	senderService.sendEmailWithAttachment("najjaramal220@gmail.com",
			"this is Email body with attachment...",
			"this email has attachment",
			"C:\\Users\\Famille\\Desktop\\pushProjetCommande.txt");
}*/
}