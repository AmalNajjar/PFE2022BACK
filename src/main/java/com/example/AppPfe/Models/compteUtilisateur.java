package com.example.AppPfe.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CompteUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer matricule;
    private String nom;
    private String prenom;
    private String  password;
    @Column(unique =true)
    private String email;
    @ManyToOne
    @JoinColumn(name ="libelleDirection",referencedColumnName = "libelleDirection")
    private Direction_Regionale libelleDirection;
}


