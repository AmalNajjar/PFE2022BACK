package com.example.AppPfe.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table
public class suivi_doc_1ereAge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String limite_de_conservation_1ere_age;
    private int Numero_document;
    private String Chapitre_comptable ;
    private int Nombre_De_pages;
    private Date Date_De_creation_Du_Document;
    private Date Date_d_entree_Du_Document;
    private int Nombre_De_documents ;
    @Column(unique =true)
    private int codedocument;
    private int emplacement_physique;
    private int numero_d_ordre;
    @ManyToOne
    @JoinColumn(name ="designation_Nomenclature",referencedColumnName = "designation_Nomenclature")
    private Nomenclature designation_Nomenclature;
    @ManyToOne
    @JoinColumn(name ="libelleDirection")
    private Direction_Regionale libelleDirection;
   //
   @ManyToOne
   @JoinColumn(name ="codedirection" )
   private Direction_Regionale codedirection;

}

