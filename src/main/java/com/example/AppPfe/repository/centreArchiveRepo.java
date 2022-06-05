package com.example.AppPfe.repository;

import com.example.AppPfe.Models.Centre_archives;
import com.example.AppPfe.Models.Centre_pre_archivage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface centreArchiveRepo extends JpaRepository<Centre_archives,Long> {
}
