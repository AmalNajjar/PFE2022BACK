package com.example.AppPfe.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codeAgence;
    private String libelleAgence;
    @ManyToOne
    @JoinColumn(name ="lieu_d_archivage_1_ere_age",referencedColumnName ="Lieu")
    private LieuArchive lieu_d_archivage_1_ere_age;
    @ManyToOne
    @JoinColumn(name ="lieu_d_archivage_2_eme_age",referencedColumnName ="Lieu")
    private LieuArchive lieu_d_archivage_2_eme_age;



    
}
