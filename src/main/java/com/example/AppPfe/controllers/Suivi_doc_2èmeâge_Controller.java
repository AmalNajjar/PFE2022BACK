package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Suivi_doc_1erâge;
import com.example.AppPfe.Models.Suivi_doc_2èmeâge;
import com.example.AppPfe.Models.Suivi_doc_3èmeâge;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Suivi_doc_2èmeâge_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Suivi_doc_2èmeâge_Controller {
    @Autowired
    Suivi_doc_2èmeâge_Interface suivi2emeage;


    @PostMapping("/SuiviDocument2eme")
    public Suivi_doc_2èmeâge SaveDocument(@RequestBody Suivi_doc_2èmeâge suivi_doc_2emeAge) {
        System.out.println(suivi_doc_2emeAge);
        return this.suivi2emeage.save(suivi_doc_2emeAge);}

    @GetMapping("/SuiviDocuments2eme")
    public List<Suivi_doc_2èmeâge> getAllDocuments() {
        return  suivi2emeage.findByDestruction(false);
    }

    @GetMapping("/SuiviDocuments2eme/deleted")
    public List<Suivi_doc_2èmeâge> getAllDocumentsDeleted() {
        return  suivi2emeage.findByDestruction(true);
    }

    @GetMapping("/SuiviDocuments2eme/{id}")
    public ResponseEntity<Suivi_doc_2èmeâge> GetDocumentById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_2èmeâge suivi_doc_2emeAge = suivi2emeage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        return ResponseEntity.ok().body(suivi_doc_2emeAge);
    }
    @DeleteMapping("/SuiviDocument2eme/{id}")
    public Map<String, Boolean> deleteDocument(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_2èmeâge suivi_doc_2emeAge = suivi2emeage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        suivi2emeage.delete(suivi_doc_2emeAge);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
    @PatchMapping("/SuiviDocument2eme/{id}")
    public Suivi_doc_2èmeâge updateDocumentDeleted(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException {
        Suivi_doc_2èmeâge suivi_doc_2emeAge = suivi2emeage.findByCodedocument(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        suivi_doc_2emeAge.setDestruction(true);
        return suivi2emeage.save(suivi_doc_2emeAge);
    }
}
