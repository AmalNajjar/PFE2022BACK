package com.example.AppPfe.controllers;
import com.example.AppPfe.Models.compteUtilisateur;
import com.example.AppPfe.exception.EmailException;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.compteUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class CompteUtilisateurController {
    @Autowired
    compteUtilisateurRepository compteRepository;

    @PostMapping("/Compte")
    public void register(@RequestBody compteUtilisateur compte) throws Exception {
        compteUtilisateur user=compteRepository.findByEmail(compte.getEmail());
        if(user != null){
            throw new EmailException("string email");
        }
        compteRepository.save(compte);
    }

    @GetMapping("/Comptes")
    public List<compteUtilisateur> getAllComptes() {
        return  compteRepository.findAll();
    }


    @GetMapping("/Comptes/{id}")
    public ResponseEntity<compteUtilisateur> getCompteById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        compteUtilisateur compte = compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte not found"));
        return ResponseEntity.ok().body(compte);
    }


    @PutMapping("/Comptes/{id}")
    public ResponseEntity<compteUtilisateur>updateCompte(@PathVariable(value = "id") Integer id,
                                                         @RequestBody compteUtilisateur compteDetails)
            throws ResourceNotFoundException {
        compteUtilisateur compte= compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("compte not found"));

        compte.setNom(compteDetails.getNom());
        compte.setPrenom(compteDetails.getPrenom());
        compte.setDirection(compteDetails.getDirection());
        compte.setEmail(compteDetails.getEmail());
        compte.setPassword(compteDetails.getPassword());
        final compteUtilisateur updateCompte  = compteRepository.save(compte);
        return ResponseEntity.ok(compte );
    }

    @DeleteMapping("/Comptes/{id}")
    public Map<String, Boolean> deleteDirection(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        compteUtilisateur compte = compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte not found"));
        compteRepository.delete(compte);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @GetMapping("/Comptes/findbyEmail/{email}")
    public compteUtilisateur trouverParEmail(@PathVariable String email){
        return compteRepository.findByEmail(email);
    }
}
