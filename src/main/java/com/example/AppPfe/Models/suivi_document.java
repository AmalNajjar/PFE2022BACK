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
@MappedSuperclass
abstract public class suivi_document implements Serializable {
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
    @OneToOne
    @JoinColumn(name ="designation_Nomenclature",referencedColumnName = "designation_Nomenclature")
    private Nomenclature designation_Nomenclature;
    //@ManyToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name ="designation_Nomenclature",referencedColumnName = "id")
    //private List<Nomenclature> designation_Nomenclature=new ArrayList<>();





}
