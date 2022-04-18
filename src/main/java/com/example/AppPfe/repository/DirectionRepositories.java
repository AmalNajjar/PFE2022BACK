package com.example.AppPfe.repository;

import com.example.AppPfe.Models.Direction_Regionale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepositories extends JpaRepository<Direction_Regionale,Long> {
 //   @Query("select d from  d where d.libelleDirection=?1")
   // Direction_Regionale findByDirection(String libelleDirection);

}
