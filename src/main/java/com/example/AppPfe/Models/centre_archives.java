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
@Table
public class Centre_archives implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code_centre;
    private String libelle_centre;
    @OneToMany
    @JsonIgnore
    private List<Suivi_doc_3èmeâge> suiviDoc3emeAgeList=new ArrayList<>();
}
