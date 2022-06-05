package com.example.AppPfe.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table
@AllArgsConstructor
public class Suivi_doc_2èmeâge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String limite_de_conservation_2eme_age;
    private int Numero_document;
    private String Chapitre_comptable;
    private int Nombre_De_pages;
    private Date date_de_versement_2eme_age;
    private Date Date_De_creation_Du_Document;
    private Date  date_d_entree_Du_Document;
    private int Nombre_De_documents ;
    @Column(unique =true)
    private int codedocument;
    private boolean destruction=false;
    private int numero_d_ordre;
    private int emplSalleConservation;
    private int emplCote;
    @ManyToOne
    @JoinColumn(name ="designation_Nomenclature",referencedColumnName = "designation_Nomenclature")
    private Nomenclature designation_Nomenclature;

    @ManyToOne
    @JoinColumn(name ="code_centre",referencedColumnName = "code_centre")
    private Centre_pre_archivage code_centre;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name ="libelleDirection")
    private Direction_Regionale libelleDirection;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name ="codedirection" )
    private Direction_Regionale codedirection;
}

