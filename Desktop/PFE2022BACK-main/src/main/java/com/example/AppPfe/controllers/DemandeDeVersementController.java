package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Agence;
import com.example.AppPfe.Models.compteUtilisateur;
import com.example.AppPfe.Models.demandeDeVersement;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.DemandeDeVermentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class DemandeDeVersementController {
    @Autowired
    DemandeDeVermentRepository demandeDeVermentRepository;
    @PostMapping("/Demande")
    public demandeDeVersement register(@RequestBody() demandeDeVersement demandeDeVersement)  {
        System.out.println(demandeDeVersement);
        return this.demandeDeVermentRepository.save(demandeDeVersement);

    }

    @GetMapping("/Demandes")
    public List<demandeDeVersement> getAllComptes() {
        return  demandeDeVermentRepository.findAll();
    }


    @GetMapping("/Demandes/{id}")
    public ResponseEntity<demandeDeVersement> getCompteById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        demandeDeVersement demande =demandeDeVermentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte not found"));
        return ResponseEntity.ok().body(demande);
    }

    @DeleteMapping("/Demandes/{id}")
    public Map<String, Boolean> deleteDemande(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        demandeDeVersement demande = demandeDeVermentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande not found"));
        demandeDeVermentRepository.delete(demande);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
