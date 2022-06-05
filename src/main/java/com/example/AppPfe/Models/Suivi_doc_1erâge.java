package com.example.AppPfe.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table

public class Suivi_doc_1erâge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String limite_de_conservation_1ere_age;
    private int Numero_document;
    private String Chapitre_comptable ;
    private int Nombre_De_pages;
    private Date Date_De_creation_Du_Document;
    private Date Date_d_entree_Du_Document;
    private boolean destruction=false;
    private int Nombre_De_documents ;
    @Column(unique =true)
    private int codedocument;
    private int empl_physique;
    private int numero_d_ordre;
    @ManyToOne
    @JoinColumn(name ="designation_Nomenclature",referencedColumnName = "designation_Nomenclature")
    private Nomenclature designation_Nomenclature;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name ="libelleDirection")
    private Direction_Regionale libelleDirection;

    @ManyToOne(cascade = CascadeType.REMOVE)
   @JoinColumn(name ="codedirection" )
   private Direction_Regionale codedirection;

}

