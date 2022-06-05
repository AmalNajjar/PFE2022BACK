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
public class Demande_consultation implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int num_dde;
    private Date date_dde;
    private String objet;
    private int num_Ordre ;
    private int nombre_de_copie;
    private String type_document;
    private Date date_retour;
    private Date date_sortie;
    //////
    @ManyToOne
    @JoinColumn(name ="libelle")
    private StructureCentrale libelle ;


    @ManyToOne
    @JoinColumn(name ="libelleAgence")
    private Agence libelleAgence ;
    ////////////////
    @ManyToOne
    @JoinColumn(name ="numero_document")
    private Suivi_doc_2èmeâge numero_document;
    @ManyToOne
    @JoinColumn(name ="codedocument")
    private Suivi_doc_2èmeâge codedocument;

    @ManyToOne
    @JoinColumn(name ="Nombre_De_documents ")
    private Suivi_doc_2èmeâge Nombre_De_documents ;
    @ManyToOne
    @JoinColumn(name =" Date_De_creation_Du_Document")
    private Suivi_doc_2èmeâge Date_De_creation_Du_Document;

}
