package com.example.AppPfe.repository;

import com.example.AppPfe.Models.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomenclatureRepositories extends JpaRepository<Nomenclature,Long> {
  //  @Query("select c from Nomenclature c where c.designation_Nomenclature=?1")
    //Nomenclature findByNomenclature(String designation_Nomenclature);
}
