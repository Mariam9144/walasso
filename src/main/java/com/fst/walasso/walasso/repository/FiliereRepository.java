package com.fst.walasso.walasso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.walasso.walasso.model.Filiere;

@Repository

public interface FiliereRepository extends JpaRepository<Filiere,Long> {

}
