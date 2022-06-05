package com.example.AppPfe.repository;

import com.example.AppPfe.Models.Direction_Regionale;
import com.example.AppPfe.Models.Suivi_doc_2èmeâge;
import com.example.AppPfe.Models.Suivi_doc_3èmeâge;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface Suivi_doc_2èmeâge_Interface extends JpaRepository<Suivi_doc_2èmeâge,Long> {
    List<Suivi_doc_2èmeâge> findByDestruction(boolean var);

     Optional<Suivi_doc_2èmeâge> findByCodedocument(int id);
    @Transactional
    void deleteAllByLibelleDirection(Direction_Regionale libelleDirection );
    @Transactional
    void deleteAllByCodedirection(Direction_Regionale codedirection);
}
