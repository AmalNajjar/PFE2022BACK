package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Agence;
import com.example.AppPfe.Models.demandeDeVersement;
import com.example.AppPfe.repository.DemandeDeVermentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class DemandeDeVersementController {
    @Autowired
    DemandeDeVermentRepository demandeDeVermentRepository;
    @PostMapping("/Demande")
    public demandeDeVersement register(@RequestBody() demandeDeVersement demandeDeVersement)  {
        System.out.println(demandeDeVersement);
        return this.demandeDeVermentRepository.save(demandeDeVersement);

    }

}
