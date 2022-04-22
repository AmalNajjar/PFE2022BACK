package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.suivi_doc_1ereAge;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Suivi1ereage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Suivi1ereageController {
    @Autowired
    Suivi1ereage suivi1ereage;



    @PostMapping("/SuiviDocument")
    public suivi_doc_1ereAge saveDocument(@RequestBody suivi_doc_1ereAge suivi_doc_1ereAge) {
        System.out.println(suivi_doc_1ereAge);
        return this.suivi1ereage.save(suivi_doc_1ereAge);}
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
   /* @PutMapping("/SuiviDocuments/{id}")
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
*/
}
