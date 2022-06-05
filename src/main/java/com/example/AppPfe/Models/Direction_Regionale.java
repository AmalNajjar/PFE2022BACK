

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
public class Direction_Regionale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codedirection;
    private String libelleDirection;
    @ManyToOne
    @JoinColumn(name ="lieu_d_archivage_1_ere_age",referencedColumnName ="Lieu")
    private LieuArchive lieu_d_archivage_1_ere_age;
    @ManyToOne
    @JoinColumn(name ="lieu_d_archivage_2_eme_age",referencedColumnName ="Lieu")
    private LieuArchive lieu_d_archivage_2_eme_age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="typeDirection",referencedColumnName = "libelle_type_dir")
    private TypeDirection typeDirection;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Suivi_doc_1erâge> suivi_documents=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Suivi_doc_2èmeâge> suivi_document2eme=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Suivi_doc_3èmeâge> suivi_document3eme=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CompteUtilisateur> compteUtilisateurs=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "Expediteur")
    @JsonIgnore
    private List<Demande_de_versement> demandeVersements = new ArrayList<>();

}
