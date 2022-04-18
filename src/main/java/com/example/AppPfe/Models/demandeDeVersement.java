package com.example.AppPfe.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class demandeDeVersement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int num_dde;
    private Date date_dde;
    private String objet;
    private int nbrCarton ;
    private String etat;
}
