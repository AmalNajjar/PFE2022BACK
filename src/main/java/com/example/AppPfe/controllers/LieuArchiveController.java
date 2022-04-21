package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.LieuArchive;
import com.example.AppPfe.repository.lieuArchiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class LieuArchiveController {
    @Autowired
    lieuArchiveRepo lieuArchiveRepository;
    @PostMapping("/lieu")
    LieuArchive savelieu(@RequestBody()LieuArchive l){
        return  this.lieuArchiveRepository.save(l);
    }
    @GetMapping("/lieu")
    List<LieuArchive> getAll(){
        return lieuArchiveRepository.findAll();
    }

    @GetMapping("/lieu/{id}")
    Optional<LieuArchive> getById(@PathVariable("id") Long id){
        System.out.println(id);
        return lieuArchiveRepository.findById(id);
    }

}
