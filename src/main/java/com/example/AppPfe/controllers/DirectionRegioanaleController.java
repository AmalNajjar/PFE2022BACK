package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Direction_Regionale;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.repository.DirectionRepositories;
import com.example.AppPfe.repository.Suivi_doc_1erâge_Interface;
import com.example.AppPfe.repository.Suivi_doc_2èmeâge_Interface;
import com.example.AppPfe.repository.Suivi_doc_3èmeâge_Interface;
import org.springframework.beans.factory.annotation.Autowired;
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
@Autowired
Suivi_doc_1erâge_Interface suivi1;
    @Autowired
    Suivi_doc_2èmeâge_Interface suivi2;
    @Autowired
    Suivi_doc_3èmeâge_Interface suivi3;
    @PostMapping("/Direction")
    public Direction_Regionale créer_direction(@RequestBody Direction_Regionale directionRegionale) {
        System.out.println(directionRegionale);
        return directionRepositories.save(directionRegionale);


    }

    @GetMapping("/Directions")
    public List<Direction_Regionale> getAllDirections() {
        return  directionRepositories.findAll();
    }

    @GetMapping("/Directions/{id}")
    public ResponseEntity<Direction_Regionale> consulter_direction(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Direction_Regionale directionRegionale = directionRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cette direction n'existe pas"));
        return ResponseEntity.ok().body(directionRegionale);
    }


    @PutMapping("/Directions/{id}")
    public ResponseEntity<Direction_Regionale> modifier_direction(@PathVariable(value = "id") Long id,
                                                               @RequestBody Direction_Regionale directionDetails)
            throws ResourceNotFoundException {
        Direction_Regionale directionRegionale= directionRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cette direction n'existe pas"));


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
    public Map<String, Boolean> supprimer_direction(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Direction_Regionale directionRegionale = directionRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cette direction n'existe pas"));
        suivi1.deleteAllByLibelleDirection(directionRegionale);
        suivi2.deleteAllByLibelleDirection(directionRegionale);
        suivi3.deleteAllByLibelleDirection(directionRegionale);

        suivi1.deleteAllByCodedirection(directionRegionale);
        suivi2.deleteAllByCodedirection(directionRegionale);
        suivi3.deleteAllByCodedirection(directionRegionale);

        directionRepositories.delete(directionRegionale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        System.out.println("direction régionale supprimée");
        return response;
    }



}
