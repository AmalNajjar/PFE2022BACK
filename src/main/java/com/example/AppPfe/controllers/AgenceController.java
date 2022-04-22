package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Agence;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.AgenceRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class AgenceController {

    @Autowired
    AgenceRepositories agenceRepositories;
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


}
