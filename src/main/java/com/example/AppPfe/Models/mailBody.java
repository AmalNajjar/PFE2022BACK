package com.example.AppPfe.Models;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class mailBody implements Serializable {
    String toEmail;
    String subject;
    emailData body;
}