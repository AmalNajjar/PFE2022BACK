package com.example.AppPfe.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class emailData implements Serializable {
    int code;
    Date date_;
    String materiel;
    String humain;
}
