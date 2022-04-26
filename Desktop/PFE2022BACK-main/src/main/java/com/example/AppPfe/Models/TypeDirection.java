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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
public class TypeDirection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeType_dir;
    private String libelle_type_dir;
    @OneToMany(mappedBy = "typeDirection")
    @JsonIgnore
    private List<Direction_Regionale> direction_regionaleList=new ArrayList<>();


    //@JsonIgnore
    //private Direction_Regionale directionRegionale;
    //@OneToMany
    //@JoinColumn(name = "libelleDirection",referencedColumnName = "libelleDirection")
    //List<Direction_Regionale> typedirection= new ArrayList<>();








}