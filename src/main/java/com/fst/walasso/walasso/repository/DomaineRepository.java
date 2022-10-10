package com.fst.walasso.walasso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.walasso.walasso.model.Domaine;

@Repository
public interface DomaineRepository  extends JpaRepository<Domaine, Integer>{

}
