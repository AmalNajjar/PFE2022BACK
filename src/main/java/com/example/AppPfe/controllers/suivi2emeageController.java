package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.suivi_doc_1ereAge;
import com.example.AppPfe.Models.suivi_doc_2emeAge;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Suivi1ereage;
import com.example.AppPfe.repository.Suivi2emeage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class suivi2emeageController {
    @Autowired
    Suivi2emeage suivi2emeage;


    @PostMapping("/SuiviDocument2eme")
    public suivi_doc_2emeAge saveDocument(@RequestBody suivi_doc_2emeAge suivi_doc_2emeAge) {
        System.out.println(suivi_doc_2emeAge);
        return this.suivi2emeage.save(suivi_doc_2emeAge);}

    @GetMapping("/SuiviDocuments2eme")
    public List<suivi_doc_2emeAge> getAllDocuments() {
        return  suivi2emeage.findAll();
    }

    @GetMapping("/SuiviDocuments2eme/{id}")
    public ResponseEntity<suivi_doc_2emeAge> getDocumentsById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        suivi_doc_2emeAge suivi_doc_2emeAge = suivi2emeage.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        return ResponseEntity.ok().body(suivi_doc_2emeAge);
    }


}
