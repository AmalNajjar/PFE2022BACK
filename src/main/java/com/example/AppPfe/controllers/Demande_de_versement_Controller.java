package com.example.AppPfe.controllers;
import com.example.AppPfe.Models.Demande_de_versement;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.Demande_de_versement_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Demande_de_versement_Controller {
    @Autowired
    Demande_de_versement_Interface demandeDeVermentRepository;
    @PostMapping("/Demande")
    public Demande_de_versement SaveDemande(@RequestBody() Demande_de_versement demandeDeVersement)  {
        System.out.println(demandeDeVersement);
        return this.demandeDeVermentRepository.save(demandeDeVersement);

    }

    @GetMapping("/Demandes")
    public List<Demande_de_versement> GetAllDemande() {
        return  demandeDeVermentRepository.findAll();
    }


    @GetMapping("/Demandes/{id}")
    public ResponseEntity<Demande_de_versement> GetDemandeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Demande_de_versement demande =demandeDeVermentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte n'existe pas"));
        return ResponseEntity.ok().body(demande);
    }

    @DeleteMapping("/Demandes/{id}")
    public Map<String, Boolean> DeleteDemande(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Demande_de_versement demande = demandeDeVermentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande n'existe pas"));
        demandeDeVermentRepository.delete(demande);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
