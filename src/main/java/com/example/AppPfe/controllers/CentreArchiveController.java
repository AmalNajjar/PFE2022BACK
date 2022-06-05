package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Centre_archives;
import com.example.AppPfe.Models.Centre_pre_archivage;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.centreArchiveRepo;
import com.example.AppPfe.repository.centrePreArchivageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class CentreArchiveController {

    @Autowired
    com.example.AppPfe.repository.centreArchiveRepo centreArchiveRepo ;

    @PostMapping("/CentreArchives")
    public Centre_archives register(@RequestBody Centre_archives  centre_archives )  {
        return this.centreArchiveRepo.save(centre_archives);

    }

    @GetMapping("/CentreArchives")
    public List<Centre_archives > getAllCentreArchives() {
        return  centreArchiveRepo.findAll();
    }

    @GetMapping("/CentreArchives/{id}")
    public ResponseEntity<Centre_archives > getCentreArchivesById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Centre_archives centre_archives  =centreArchiveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("centre archive n'existe pas"));
        return ResponseEntity.ok().body(centre_archives );
    }


    @PutMapping("/CentreArchives/{id}")
    public ResponseEntity<Centre_archives > updateCentre(@PathVariable(value = "id") Long id,
                                                             @RequestBody Centre_archives  centre_archivesDetails)
            throws ResourceNotFoundException {
        Centre_archives  centre_archives = centreArchiveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("centre archive n'existe pas"));


        centre_archives.setCode_centre(centre_archivesDetails.getCode_centre());
        centre_archives.setLibelle_centre(centre_archivesDetails.getLibelle_centre());

        centre_archives.setId(centre_archivesDetails.getId());

        final Centre_archives updateCentre = centreArchiveRepo.save(centre_archives);
        return ResponseEntity.ok(updateCentre);
    }

    @DeleteMapping("/CentreArchives/{id}")
    public Map<String, Boolean> deleteCentre(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Centre_archives centre_archives=centreArchiveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("centre archive n'existe pas"));
        centreArchiveRepo.delete(centre_archives);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}

