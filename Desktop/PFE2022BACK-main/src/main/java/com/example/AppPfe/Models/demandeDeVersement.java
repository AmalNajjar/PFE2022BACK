package com.example.AppPfe.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class demandeDeVersement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num_dde;
    private Date date_dde;
    private String objet;
    private int nbrCarton ;
    private String etat;
    @ManyToOne
    @JoinColumn(name ="Destinataire",referencedColumnName ="Lieu")
    private LieuArchive Destinataire;
    @ManyToOne
    @JoinColumn(name ="Expediteur",referencedColumnName ="libelleDirection")
    private Direction_Regionale Expediteur;
    @ManyToOne
    @JoinColumn(name ="designation_nomenclature",referencedColumnName ="designation_Nomenclature")
    private Nomenclature designation_nomenclature;
}
