package com.example.AppPfe.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Nomenclature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code_Nomenclature;
    private String designation_Nomenclature;
    private String dureeConservation_1ereAge;
    private String dureeConservation_2emeAge;
    private String valeurHistorique_3emeAge;
    @OneToMany(mappedBy = "designation_Nomenclature")
    @JsonIgnore
    private List<Suivi_doc_1erâge> suivi_document=new ArrayList<>();
    @OneToMany(mappedBy = "designation_nomenclature")
    @JsonIgnore
    private List<Demande_de_versement> demandedeVersements = new ArrayList<>();
    @OneToMany(mappedBy = "designation_Nomenclature")
    @JsonIgnore
    private List<Suivi_doc_2èmeâge> suivi_documents=new ArrayList<>();
}