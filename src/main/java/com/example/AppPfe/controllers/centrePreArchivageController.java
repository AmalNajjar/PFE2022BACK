package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Centre_pre_archivage;
import com.example.AppPfe.exception.ResourceNotFoundException;
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
public class centrePreArchivageController {

    @Autowired
    centrePreArchivageRepo centrePreArchivageRepo ;

    @PostMapping("/CentrePreArchivage")
    public Centre_pre_archivage register(@RequestBody Centre_pre_archivage centrePreArchivage)  {
        return this.centrePreArchivageRepo.save(centrePreArchivage);

    }

    @GetMapping("/CentrePreArchivages")
    public List<Centre_pre_archivage> getAllCentrePreArchivage() {
        return  centrePreArchivageRepo.findAll();
    }

    @GetMapping("/CentrePreArchivages/{id}")
    public ResponseEntity<Centre_pre_archivage> getCentrePreArchivageById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Centre_pre_archivage centre_pre_archivagee =centrePreArchivageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre de pré-Archivage n'existe pas"));
        return ResponseEntity.ok().body(centre_pre_archivagee);
    }


    @PutMapping("/CentrePreArchivages/{id}")
    public ResponseEntity<Centre_pre_archivage> updateCentrePreArchivage(@PathVariable(value = "id") Long id,
                                                             @RequestBody Centre_pre_archivage centre_pre_archivageDetails)
            throws ResourceNotFoundException {
        Centre_pre_archivage centre_pre_archivagee= centrePreArchivageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre de pré-Archivage n'existe pas"));


        centre_pre_archivagee.setCode_centre(centre_pre_archivageDetails.getCode_centre());
        centre_pre_archivagee.setLibelle_centre(centre_pre_archivageDetails.getLibelle_centre());

        centre_pre_archivagee.setId(centre_pre_archivageDetails.getId());

        final Centre_pre_archivage updateCentre = centrePreArchivageRepo.save(centre_pre_archivagee);
        return ResponseEntity.ok(updateCentre);
    }

    @DeleteMapping("/CentrePreArchivages/{id}")
    public Map<String, Boolean> deleteCentrePreArchivage(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Centre_pre_archivage centre_pre_archivagee=centrePreArchivageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre de pré-Archivage n'existe pas"));
        centrePreArchivageRepo.delete(centre_pre_archivagee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
