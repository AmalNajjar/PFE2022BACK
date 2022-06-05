package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.StructureCentrale;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.structureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class StructureController {

    @Autowired
    structureRepository structureRepository;


    @PostMapping("/Structure")
    public StructureCentrale créer_structure(@RequestBody StructureCentrale structure) {
        System.out.println(structure);
        return structureRepository.save(structure);
    }


    @GetMapping("/Structures")
    public List<StructureCentrale> getAllStructures() {

        return  structureRepository.findAll();
    }

    @GetMapping("/Structures/{id}")
    public ResponseEntity<StructureCentrale> consulter_structure(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        StructureCentrale structure= structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure n'existe pas"));
        return ResponseEntity.ok().body(structure);
    }


    @PutMapping("/Structures/{id}")
    public ResponseEntity<StructureCentrale>modifier_structure(@PathVariable(value = "id") Long id,
                                                               @RequestBody StructureCentrale structureDetails)
            throws ResourceNotFoundException {
        StructureCentrale structure= structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure n'existe pas"));

        structure.setCode(structureDetails.getCode());
        structure.setLibelle(structureDetails.getLibelle());
        structure.setLieu_archivage1ereAge(structureDetails.getLieu_archivage1ereAge());
        structure.setLieu_archivage2emeAge(structureDetails.getLieu_archivage2emeAge());
        final StructureCentrale updateStructure  = structureRepository.save(structure);
        return ResponseEntity.ok(updateStructure);
    }

    @DeleteMapping("/Structures/{id}")
    public Map<String, Boolean> supprimer_structure(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        StructureCentrale structure= structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure n'existe pas"));
        structureRepository.delete(structure);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
