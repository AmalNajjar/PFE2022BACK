package com.example.AppPfe.repository;

import com.example.AppPfe.Models.LieuArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface lieuArchiveRepo extends JpaRepository<LieuArchive,Long> {
    @Query("select a from LieuArchive a where a.Lieu=?1")
    LieuArchive findByLieu(String Lieu);
}
