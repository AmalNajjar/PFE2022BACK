package com.example.AppPfe.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
public class StructureCentrale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String libelle;

    @ManyToOne
    @JoinColumn(name ="lieu_archivage1ereAge",referencedColumnName ="Lieu")
    private LieuArchive lieu_archivage1ereAge;

    @ManyToOne
    @JoinColumn(name ="lieu_archivage2emeAge",referencedColumnName ="Lieu")
    private LieuArchive lieu_archivage2emeAge;

}

