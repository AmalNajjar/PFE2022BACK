package com.example.AppPfe.repository;

import com.example.AppPfe.Models.StructureCentrale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface structureRepository extends JpaRepository<StructureCentrale,Long> {



    /*  public structureCentrale findByEmail(String email);*/
}

