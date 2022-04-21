package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Direction_Regionale;
import com.example.AppPfe.repository.DirectionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class DirectionRegioanaleController {
    @Autowired
    DirectionRepositories directionRepositories;

    @PostMapping("/Direction")
    public Direction_Regionale register(@RequestBody Direction_Regionale directionRegionale) {
        System.out.println(directionRegionale);
        return directionRepositories.save(directionRegionale);


    }

    @GetMapping("/Directions")
    public List<Direction_Regionale> getAllDirections() {
        return  directionRepositories.findAll();
    }

    @GetMapping("/Directions/{id}")
    public ResponseEntity<Direction_Regionale> getDirectionById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Direction_Regionale directionRegionale = directionRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direction not found"));
        return ResponseEntity.ok().body(directionRegionale);
    }


    @PutMapping("/Directions/{id}")
    public ResponseEntity<Direction_Regionale> updateDirection(@PathVariable(value = "id") Long id,
                                                               @RequestBody Direction_Regionale directionDetails)
            throws ResourceNotFoundException {
        Direction_Regionale directionRegionale= directionRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("direction not found"));


        directionRegionale.setCodedirection(directionDetails.getCodedirection());
        directionRegionale.setLibelleDirection(directionDetails.getLibelleDirection());

        directionRegionale.setId(directionDetails.getId());
        directionRegionale.setTypeDirection(directionDetails.getTypeDirection());
        directionRegionale.setLieu_d_archivage_1_ere_age(directionDetails.getLieu_d_archivage_1_ere_age());
        directionRegionale.setLieu_d_archivage_2_eme_age(directionDetails.getLieu_d_archivage_2_eme_age());

        final Direction_Regionale updatedDirection = directionRepositories.save(directionRegionale);
        return ResponseEntity.ok(updatedDirection );
    }

    @DeleteMapping("/Directions/{id}")
    public Map<String, Boolean> deleteDirection(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Direction_Regionale directionRegionale = directionRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direction not found"));
        directionRepositories.delete(directionRegionale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }



}
