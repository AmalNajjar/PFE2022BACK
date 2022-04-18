package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.*;
import com.example.AppPfe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/test")
public class TestController {

  @Autowired
  DirectionRepositories directionRepositories;
  @Autowired
  AgenceRepositories agenceRepositories;
  @Autowired
  NomenclatureRepositories nomenclatureRepositories ;
  @Autowired
 Suivi1ereage suivi1ereage;
  @Autowired
  lieuArchiveRepo lieuArchiveRepository;
  @Autowired
  TypeDirectionrepo typeDirectionrepo;
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/auth")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {

    return "Admin Board.";
  }

    @GetMapping("/lieu")
    List<LieuArchive> getAll(){
        return lieuArchiveRepository.findAll();
    }

    @GetMapping("/lieu/{id}")
    Optional<LieuArchive> getById(@PathVariable("id") Long id){
        System.out.println(id);
        return lieuArchiveRepository.findById(id);
    }
    @GetMapping("/type")
    List<TypeDirection> gettAll(){
        return typeDirectionrepo.findAll();
    }

    @GetMapping("/type/{id}")
    Optional<TypeDirection> gettById(@PathVariable("id") Long id){
        System.out.println(id);
        return typeDirectionrepo.findById(id);
    }





  @PostMapping("/Direction")
  public Direction_Regionale register(@RequestBody Direction_Regionale directionRegionale) {
      System.out.println(directionRegionale);
    return directionRepositories.save(directionRegionale);


  }

  @GetMapping("/Directions")
  public List<Direction_Regionale> getAllDirections() {
    return  directionRepositories.findAll();
  }

  @GetMapping("/Directions/{id}")
  public ResponseEntity<Direction_Regionale> getDirectionById(@PathVariable(value = "id") Long id)
          throws ResourceNotFoundException {
    Direction_Regionale directionRegionale = directionRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Direction not found"));
    return ResponseEntity.ok().body(directionRegionale);
  }


  @PutMapping("/Directions/{id}")
  public ResponseEntity<Direction_Regionale> updateDirection(@PathVariable(value = "id") Long id,
                                                             @RequestBody Direction_Regionale directionDetails)
          throws ResourceNotFoundException {
    Direction_Regionale directionRegionale= directionRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("direction not found"));


    directionRegionale.setCodedirection(directionDetails.getCodedirection());
    directionRegionale.setLibelleDirection(directionDetails.getLibelleDirection());

    directionRegionale.setId(directionDetails.getId());
    directionRegionale.setTypeDirection(directionDetails.getTypeDirection());
    directionRegionale.setLieu_d_archivage_1_ere_age(directionDetails.getLieu_d_archivage_1_ere_age());
    directionRegionale.setLieu_d_archivage_2_eme_age(directionDetails.getLieu_d_archivage_2_eme_age());

    final Direction_Regionale updatedDirection = directionRepositories.save(directionRegionale);
    return ResponseEntity.ok(updatedDirection );
  }

  @DeleteMapping("/Directions/{id}")
  public Map<String, Boolean> deleteDirection(@PathVariable(value = "id") Long id)
          throws ResourceNotFoundException {
    Direction_Regionale directionRegionale = directionRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Direction not found"));
    directionRepositories.delete(directionRegionale);
    Map<String, Boolean> response = new HashMap<>();
    response.put("delete", Boolean.TRUE);
    return response;
  }



  //gestion Agence


  @PostMapping("/Agence")
  public Agence register(@RequestBody Agence agence)  {
    System.out.println(agence);
   return agenceRepositories.save(agence);

  }

  @GetMapping("/Agences")
  public List<Agence> getAllAgence() {
    return  agenceRepositories.findAll();
  }

  @GetMapping("/Agences/{id}")
  public ResponseEntity<Agence> getAgenceById(@PathVariable(value = "id") Integer id)
          throws ResourceNotFoundException {
    Agence agence = agenceRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agence not found"));
    return ResponseEntity.ok().body(agence);
  }


  @PutMapping("/Agences/{id}")
  public ResponseEntity<Agence> updateAgence(@PathVariable(value = "id") Integer id,
                                                             @RequestBody Agence agenceDetails)
          throws ResourceNotFoundException {
    Agence agence= agenceRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agence not found"));


    agence.setCodeAgence(agenceDetails.getCodeAgence());
    agence.setLibelleAgence(agenceDetails.getLibelleAgence());

    agence.setId(agenceDetails.getId());

   agence.setLieu_d_archivage_1_ere_age(agenceDetails.getLieu_d_archivage_1_ere_age());
    agence.setLieu_d_archivage_2_eme_age(agenceDetails.getLieu_d_archivage_2_eme_age());

    final Agence updatedAgence  = agenceRepositories.save(agence);
    return ResponseEntity.ok(updatedAgence );
  }

  @DeleteMapping("/Agences/{id}")
  public Map<String, Boolean> deleteAgence(@PathVariable(value = "id") Integer id)
          throws ResourceNotFoundException {
    Agence agence= agenceRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agence not found"));
   agenceRepositories.delete(agence);
    Map<String, Boolean> response = new HashMap<>();
    response.put("delete", Boolean.TRUE);
    return response;
  }



//gestion Nomenclature

  @PostMapping("/Nomenclature")
  public void register(@RequestBody Nomenclature nomenclature)  {
    nomenclatureRepositories.save(nomenclature);

  }

  @GetMapping("/Nomenclatures")
  public List<Nomenclature> getAllNomenclatures() {
    return  nomenclatureRepositories.findAll();
  }

  @GetMapping("/Nomenclatures/{id}")
  public ResponseEntity<Nomenclature> getNomenclatureById(@PathVariable(value = "id") Long id)
          throws ResourceNotFoundException {
  Nomenclature nomenclature =nomenclatureRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));
    return ResponseEntity.ok().body(nomenclature);
  }


  @PutMapping("/Nomenclatures/{id}")
  public ResponseEntity<Nomenclature> updateNomenclature(@PathVariable(value = "id") Long id,
                                             @RequestBody Nomenclature nomenclatureDetails)
          throws ResourceNotFoundException {
    Nomenclature nomenclature= nomenclatureRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));


    nomenclature.setCode_Nomenclature(nomenclatureDetails.getCode_Nomenclature());
    nomenclature.setDesignation_Nomenclature(nomenclatureDetails.getDesignation_Nomenclature());

    nomenclature.setId(nomenclatureDetails.getId());

    nomenclature.setDureeConservation_1ereAge(nomenclatureDetails.getDureeConservation_1ereAge());
    nomenclature.setDureeConservation_2emeAge(nomenclatureDetails.getDureeConservation_2emeAge());
    nomenclature.setValeurHistorique_3emeAge(nomenclatureDetails.getValeurHistorique_3emeAge());

    final Nomenclature updateNomenclature = nomenclatureRepositories.save(nomenclature);
    return ResponseEntity.ok(updateNomenclature );
  }

  @DeleteMapping("/Nomenclatures/{id}")
  public Map<String, Boolean> deleteNomenclature(@PathVariable(value = "id") Long id)
          throws ResourceNotFoundException {
    Nomenclature nomenclature=nomenclatureRepositories.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));
    nomenclatureRepositories.delete(nomenclature);
    Map<String, Boolean> response = new HashMap<>();
    response.put("delete", Boolean.TRUE);
    return response;
  }
//traitement 1 ere age

    @PostMapping("/SuiviDocument")
    public void saveDocument(@RequestBody suivi_doc_1ereAge suivi_doc_1ereAge) {
      suivi1ereage.save(suivi_doc_1ereAge);}
  @GetMapping("/SuiviDocuments")
  public List<suivi_doc_1ereAge> getAllDocuments() {
    return  suivi1ereage.findAll();
  }
  @GetMapping("/SuiviDocuments/{id}")
  public ResponseEntity<suivi_doc_1ereAge> getDocumentsById(@PathVariable(value = "id") Long id)
          throws ResourceNotFoundException {
    suivi_doc_1ereAge suivi_doc_1ereAge = suivi1ereage.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    return ResponseEntity.ok().body(suivi_doc_1ereAge);
  }
    @PutMapping("/SuiviDocuments/{id}")
    public ResponseEntity<suivi_doc_1ereAge> updateDoc1ereage(@PathVariable(value = "id") Long id,
                                                           @RequestBody suivi_doc_1ereAge suivi_doc_1ereAgeDetails)
            throws ResourceNotFoundException {
      suivi_doc_1ereAge suivi_doc_1ereAge= suivi1ereage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));


        suivi_doc_1ereAge.setLibelleDirection(suivi_doc_1ereAgeDetails.getLibelleDirection());
        suivi_doc_1ereAge.setDesignation_Nomenclature(suivi_doc_1ereAgeDetails.getDesignation_Nomenclature());

        suivi_doc_1ereAge.setId(suivi_doc_1ereAgeDetails.getId());

       suivi_doc_1ereAge.setChapitre_comptable(suivi_doc_1ereAgeDetails.getChapitre_comptable());
        suivi_doc_1ereAge.setDate_d_entree_Du_Document(suivi_doc_1ereAgeDetails.getDate_d_entree_Du_Document());
       suivi_doc_1ereAge.setDate_De_creation_Du_Document(suivi_doc_1ereAgeDetails.getDate_De_creation_Du_Document());
        suivi_doc_1ereAge.setLimite_de_conservation_1ere_age(suivi_doc_1ereAgeDetails.getLimite_de_conservation_1ere_age());
        suivi_doc_1ereAge.setNombre_De_documents(suivi_doc_1ereAgeDetails.getNombre_De_documents());
        suivi_doc_1ereAge.setNumero_document(suivi_doc_1ereAgeDetails.getNumero_document());
        suivi_doc_1ereAge.setNombre_De_pages(suivi_doc_1ereAgeDetails.getNombre_De_pages());


        final suivi_doc_1ereAge updateDoc1ereage = suivi1ereage.save(suivi_doc_1ereAge);
        return ResponseEntity.ok(updateDoc1ereage);
    }

}