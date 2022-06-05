package com.example.AppPfe.repository;

import com.example.AppPfe.Models.CompteUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface compteUtilisateurRepository extends JpaRepository<CompteUtilisateur,Integer> {
    CompteUtilisateur findByEmail(String email);


}
