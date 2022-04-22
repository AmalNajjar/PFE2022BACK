package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Nomenclature;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.NomenclatureRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class NomenclatureController {

    @Autowired
    NomenclatureRepositories nomenclatureRepositories ;
    @PostMapping("/Nomenclature")
    public Nomenclature register(@RequestBody Nomenclature nomenclature)  {
        return this.nomenclatureRepositories.save(nomenclature);

    }

    @GetMapping("/Nomenclatures")
    public List<Nomenclature> getAllNomenclatures() {
        return  nomenclatureRepositories.findAll();
    }

    @GetMapping("/Nomenclatures/{id}")
    public ResponseEntity<Nomenclature> getNomenclatureById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Nomenclature nomenclature =nomenclatureRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));
        return ResponseEntity.ok().body(nomenclature);
    }


    @PutMapping("/Nomenclatures/{id}")
    public ResponseEntity<Nomenclature> updateNomenclature(@PathVariable(value = "id") Long id,
                                                           @RequestBody Nomenclature nomenclatureDetails)
            throws ResourceNotFoundException {
        Nomenclature nomenclature= nomenclatureRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));


        nomenclature.setCode_Nomenclature(nomenclatureDetails.getCode_Nomenclature());
        nomenclature.setDesignation_Nomenclature(nomenclatureDetails.getDesignation_Nomenclature());

        nomenclature.setId(nomenclatureDetails.getId());

        nomenclature.setDureeConservation_1ereAge(nomenclatureDetails.getDureeConservation_1ereAge());
        nomenclature.setDureeConservation_2emeAge(nomenclatureDetails.getDureeConservation_2emeAge());
        nomenclature.setValeurHistorique_3emeAge(nomenclatureDetails.getValeurHistorique_3emeAge());

        final Nomenclature updateNomenclature = nomenclatureRepositories.save(nomenclature);
        return ResponseEntity.ok(updateNomenclature );
    }

    @DeleteMapping("/Nomenclatures/{id}")
    public Map<String, Boolean> deleteNomenclature(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Nomenclature nomenclature=nomenclatureRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));
        nomenclatureRepositories.delete(nomenclature);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
