package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Nomenclature;
import com.example.AppPfe.Models.centre_pre_archivage;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.NomenclatureRepositories;
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
    public centre_pre_archivage register(@RequestBody centre_pre_archivage centrePreArchivage)  {
        return this.centrePreArchivageRepo.save(centrePreArchivage);

    }

    @GetMapping("/CentrePreArchivages")
    public List<centre_pre_archivage> getAllCentrePreArchivage() {
        return  centrePreArchivageRepo.findAll();
    }

    @GetMapping("/CentrePreArchivages/{id}")
    public ResponseEntity<centre_pre_archivage> getNomenclatureById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        centre_pre_archivage centre_pre_archivagee =centrePreArchivageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));
        return ResponseEntity.ok().body(centre_pre_archivagee);
    }


    @PutMapping("/CentrePreArchivages/{id}")
    public ResponseEntity<centre_pre_archivage> updateCentre(@PathVariable(value = "id") Long id,
                                                             @RequestBody centre_pre_archivage centre_pre_archivageDetails)
            throws ResourceNotFoundException {
        centre_pre_archivage centre_pre_archivagee= centrePreArchivageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclature not found"));


        centre_pre_archivagee.setCode_centre(centre_pre_archivageDetails.getCode_centre());
        centre_pre_archivagee.setLibelle_centre(centre_pre_archivageDetails.getLibelle_centre());

        centre_pre_archivagee.setId(centre_pre_archivageDetails.getId());

        final centre_pre_archivage updateCentre = centrePreArchivageRepo.save(centre_pre_archivagee);
        return ResponseEntity.ok(updateCentre);
    }

    @DeleteMapping("/CentrePreArchivages/{id}")
    public Map<String, Boolean> deleteNomenclature(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        centre_pre_archivage centre_pre_archivagee=centrePreArchivageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found"));
        centrePreArchivageRepo.delete(centre_pre_archivagee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
