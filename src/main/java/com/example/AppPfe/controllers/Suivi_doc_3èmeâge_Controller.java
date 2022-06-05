package com.example.AppPfe.controllers;


import com.example.AppPfe.Models.Suivi_doc_3èmeâge;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Suivi_doc_3èmeâge_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Suivi_doc_3èmeâge_Controller {
    @Autowired
    Suivi_doc_3èmeâge_Interface suivi3emeage;

    @PostMapping("/SuiviDocument3eme")
    public Suivi_doc_3èmeâge saveDocument(@RequestBody Suivi_doc_3èmeâge suivi_doc_3emeAge) {
        System.out.println(suivi_doc_3emeAge);
        return this.suivi3emeage.save(suivi_doc_3emeAge);}

    @GetMapping("/SuiviDocuments3eme")
    public List<Suivi_doc_3èmeâge> getAllDocuments() {
        return  suivi3emeage.findByDestrcution(false);
    }

    @GetMapping("/SuiviDocuments3eme/deleted")
    public List<Suivi_doc_3èmeâge> getAllDocumentsDeleted() {
        return  suivi3emeage.findByDestrcution(true);
    }

    @GetMapping("/SuiviDocuments3eme/{id}")
    public ResponseEntity<Suivi_doc_3èmeâge> getDocumentById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_3èmeâge suivi_doc_3emeAge = suivi3emeage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        return ResponseEntity.ok().body(suivi_doc_3emeAge);
    }
    @DeleteMapping("/SuiviDocument3eme/{id}")
    public Map<String, Boolean> deleteDocument(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_3èmeâge suivi_doc_3emeAge = suivi3emeage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        suivi3emeage.delete(suivi_doc_3emeAge);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @PatchMapping("/SuiviDocument3eme/{id}")
    public Suivi_doc_3èmeâge updateDocumentDeleted(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Suivi_doc_3èmeâge suivi_doc_3emeAge = suivi3emeage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document n'existe pas"));
        suivi_doc_3emeAge.setDestrcution(true);
        return suivi3emeage.save(suivi_doc_3emeAge);
    }

}
