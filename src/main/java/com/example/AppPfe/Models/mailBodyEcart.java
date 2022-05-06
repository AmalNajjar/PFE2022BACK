package com.example.AppPfe.Models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class mailBodyEcart implements Serializable {
    String toEmail;
    String subject;
    String body;
}
