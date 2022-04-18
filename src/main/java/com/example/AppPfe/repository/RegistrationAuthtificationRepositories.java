package com.example.AppPfe.repository;


import com.example.AppPfe.Models.Authentification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  RegistrationAuthtificationRepositories extends JpaRepository<Authentification,Long> {


    Optional<Authentification> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

//Authentification findByEmailAndPassword(String email, String password);
}
