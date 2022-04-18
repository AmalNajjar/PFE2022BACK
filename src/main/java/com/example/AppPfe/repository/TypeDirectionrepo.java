package com.example.AppPfe.repository;

import com.example.AppPfe.Models.TypeDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeDirectionrepo extends JpaRepository<TypeDirection,Long> {
    @Query("select b from TypeDirection b where b.libelle_type_dir=?1")
    TypeDirection findByType(String libelle_type_dir);
}
