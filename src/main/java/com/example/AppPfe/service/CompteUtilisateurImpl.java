package com.example.AppPfe.service;

import com.example.AppPfe.Models.compteUtilisateur;
import com.example.AppPfe.repository.compteUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteUtilisateurImpl implements compteUtilisateurService{
    @Autowired
    private compteUtilisateurRepository repository;

    @Override
    public Integer savecompteUtilisateur(compteUtilisateur compte) {
        return repository.save(compte).getId();
    }
}
