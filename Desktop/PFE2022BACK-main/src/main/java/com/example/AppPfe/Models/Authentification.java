package com.example.AppPfe.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@ToString
@Entity
@Table(
        uniqueConstraints = {
 @UniqueConstraint(columnNames = "email")
        })

public class Authentification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String  username;
    private String password;

    private String email;
    @ManyToMany(fetch = FetchType.LAZY)

    private Set<Role> roles = new HashSet<>();


    public Authentification() {

    }
    public Authentification(String username, String email, String encode) {
        this.username=username;
        this.email=email;
        this.password=encode;
    }



}
