package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.LieuArchive;
import com.example.AppPfe.Models.structureCentrale;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.lieuArchiveRepo;
import com.example.AppPfe.repository.structureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class StructureController {

    @Autowired
    structureRepository structureRepository;


    @PostMapping("/Structure")
    public structureCentrale registerr(@RequestBody structureCentrale structure) {
        System.out.println(structure);
        return structureRepository.save(structure);
    }


    @GetMapping("/Structures")
    public List<structureCentrale> getAllStructures() {

        return  structureRepository.findAll();
    }

    @GetMapping("/Structures/{id}")
    public ResponseEntity<structureCentrale> getStructureById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        structureCentrale structure= structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        return ResponseEntity.ok().body(structure);
    }


    @PutMapping("/Structures/{id}")
    public ResponseEntity<structureCentrale>updateStructure(@PathVariable(value = "id") Long id,
                                                            @RequestBody structureCentrale structureDetails)
            throws ResourceNotFoundException {
        structureCentrale structure= structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));

        structure.setCode(structureDetails.getCode());
        structure.setLibelle(structureDetails.getLibelle());
        structure.setLieu_archivage1ereAge(structureDetails.getLieu_archivage1ereAge());
        structure.setLieu_archivage2emeAge(structureDetails.getLieu_archivage2emeAge());
        final structureCentrale updateStructure  = structureRepository.save(structure);
        return ResponseEntity.ok(structure );
    }

    @DeleteMapping("/Structures/{id}")
    public Map<String, Boolean> deleteStructure(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        structureCentrale structure= structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        structureRepository.delete(structure);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
