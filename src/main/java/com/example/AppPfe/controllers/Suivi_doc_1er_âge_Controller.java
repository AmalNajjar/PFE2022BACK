package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Suivi_doc_1erâge;
import com.example.AppPfe.Models.Suivi_doc_2èmeâge;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Suivi_doc_1erâge_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Suivi_doc_1er_âge_Controller {
    @Autowired
    Suivi_doc_1erâge_Interface suivi1ereage;



    @PostMapping("/SuiviDocument")
    public Suivi_doc_1erâge SaveDocument(@RequestBody Suivi_doc_1erâge suivi_doc_1ereAge) {
        System.out.println(suivi_doc_1ereAge);
        return this.suivi1ereage.save(suivi_doc_1ereAge);}

    @GetMapping("/SuiviDocuments")
    public List<Suivi_doc_1erâge> getAllDocuments() {
        return  suivi1ereage.findByDestruction(false);
    }

    @GetMapping("/SuiviDocuments/deleted")
    public List<Suivi_doc_1erâge> getAllDocumentsDeleted() {
        return  suivi1ereage.findByDestruction(true);
    }

    @GetMapping("/SuiviDocuments/{id}")
    public ResponseEntity<Suivi_doc_1erâge> GetDocumentById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_1erâge suivi_doc_1ereAge = suivi1ereage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        return ResponseEntity.ok().body(suivi_doc_1ereAge);
    }
    @PutMapping("/SuiviDocuments/{id}")
    public ResponseEntity<Suivi_doc_1erâge> UpdateDocument(@PathVariable(value = "id") Long id,
                                                           @RequestBody Suivi_doc_1erâge suivi_doc_1ereAgeDetails)
            throws ResourceNotFoundException {
        Suivi_doc_1erâge suivi_doc_1ereAge= suivi1ereage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));


        suivi_doc_1ereAge.setLibelleDirection(suivi_doc_1ereAgeDetails.getLibelleDirection());
        suivi_doc_1ereAge.setDesignation_Nomenclature(suivi_doc_1ereAgeDetails.getDesignation_Nomenclature());

        suivi_doc_1ereAge.setId(suivi_doc_1ereAgeDetails.getId());
        suivi_doc_1ereAge.setNumero_d_ordre(suivi_doc_1ereAgeDetails.getNumero_d_ordre());
        suivi_doc_1ereAge.setEmpl_physique(suivi_doc_1ereAgeDetails.getEmpl_physique());
        suivi_doc_1ereAge.setCodedocument(suivi_doc_1ereAgeDetails.getCodedocument());
        suivi_doc_1ereAge.setChapitre_comptable(suivi_doc_1ereAgeDetails.getChapitre_comptable());
        suivi_doc_1ereAge.setDate_d_entree_Du_Document(suivi_doc_1ereAgeDetails.getDate_d_entree_Du_Document());
        suivi_doc_1ereAge.setDate_De_creation_Du_Document(suivi_doc_1ereAgeDetails.getDate_De_creation_Du_Document());
        suivi_doc_1ereAge.setLimite_de_conservation_1ere_age(suivi_doc_1ereAgeDetails.getLimite_de_conservation_1ere_age());
        suivi_doc_1ereAge.setNombre_De_documents(suivi_doc_1ereAgeDetails.getNombre_De_documents());
        suivi_doc_1ereAge.setNumero_document(suivi_doc_1ereAgeDetails.getNumero_document());
        suivi_doc_1ereAge.setNombre_De_pages(suivi_doc_1ereAgeDetails.getNombre_De_pages());
        suivi_doc_1ereAge.setDesignation_Nomenclature(suivi_doc_1ereAgeDetails.getDesignation_Nomenclature());
        suivi_doc_1ereAge.setLibelleDirection(suivi_doc_1ereAgeDetails.getLibelleDirection());

        final Suivi_doc_1erâge updateDoc1ereage = suivi1ereage.save(suivi_doc_1ereAge);
        return ResponseEntity.ok(updateDoc1ereage);
    }
    @DeleteMapping("/SuiviDocuments/{id}")
    public Map<String, Boolean> DeleteDocument(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_1erâge suivi_doc_1ereAge = suivi1ereage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        suivi1ereage.delete(suivi_doc_1ereAge);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
        @PatchMapping("/SuiviDocument/{id}")
        public Suivi_doc_1erâge updateDocumentDeleted(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException {
            Suivi_doc_1erâge suivi_doc_1erâge = suivi1ereage.findByCodedocument(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
            suivi_doc_1erâge.setDestruction(true);
            return suivi1ereage.save(suivi_doc_1erâge);

    }

}
