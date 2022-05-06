

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
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name ="typeDirection",referencedColumnName = "libelle_type_dir")
    private TypeDirection typeDirection;
    @OneToMany
    @JsonIgnore
    private List<suivi_doc_1ereAge> suivi_documents=new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<suivi_doc_2emeAge> suivi_document2eme=new ArrayList<>();


    @OneToMany(mappedBy = "Expediteur")
    @JsonIgnore
    private List<demandeDeVersement> demandeVersements = new ArrayList<>();

}
