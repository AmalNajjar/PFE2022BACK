package com.example.AppPfe.service;

import com.example.AppPfe.Models.CompteUtilisateur;
import com.example.AppPfe.repository.compteUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteUtilisateurImpl implements compteUtilisateurService{
    @Autowired
    private compteUtilisateurRepository repository;

    @Override
    public Integer savecompteUtilisateur(CompteUtilisateur compte) {
        return repository.save(compte).getId();
    }
}
