package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Demande_consultation;
import com.example.AppPfe.Models.Demande_consultation;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Demande_consultation_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Demande_consultation_Controller {
    @Autowired
    Demande_consultation_Interface demandeConsultationRepositories;
    @PostMapping("/DemandeConsultation")
    public Demande_consultation saveDemandeConsultation(@RequestBody() Demande_consultation demandeDeConsultation)  {
        System.out.println(demandeDeConsultation);
        return this.demandeConsultationRepositories.save(demandeDeConsultation);

    }

    @GetMapping("/DemandesConsultation")
    public List<Demande_consultation> getAllDemandeConsultation() {
        return  demandeConsultationRepositories.findAll();
    }


    @GetMapping("/DemandesConsultation/{id}")
    public ResponseEntity<Demande_consultation> getDemandeConsultationById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Demande_consultation demande = demandeConsultationRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande n'existe pas"));
        return ResponseEntity.ok().body(demande);
    }

    @DeleteMapping("/DemandesConsultation/{id}")
    public Map<String, Boolean> deleteDemandeConsultation(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Demande_consultation demande = demandeConsultationRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande n'existe pas "));
        demandeConsultationRepositories.delete(demande);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }


}