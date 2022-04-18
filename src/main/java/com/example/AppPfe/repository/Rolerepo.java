package com.example.AppPfe.repository;


import com.example.AppPfe.Models.ERole;
import com.example.AppPfe.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Rolerepo extends JpaRepository<Role,Long> {


    Optional<Role> findByName(ERole name);


}
