package com.example.AppPfe.repository;

import com.example.AppPfe.Models.structureCentrale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface structureRepository extends JpaRepository<structureCentrale,Long> {



    /*  public structureCentrale findByEmail(String email);*/
}

