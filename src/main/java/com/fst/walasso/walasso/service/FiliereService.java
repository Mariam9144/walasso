package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.Filiere;
import com.fst.walasso.walasso.repository.FiliereRepository;

import lombok.Data;

@Data
@Service


public class FiliereService {
	@Autowired
	private FiliereRepository filiereRepository;

    public Optional<Filiere> getFiliere(final long id) {
        return filiereRepository.findById(id);
    }

    public List<Filiere> getFilieres() {
        return filiereRepository.findAll();
    }

    public void deleteFiliere(final long id) {
    	filiereRepository.deleteById(id);
    }

    public Filiere saveFiliere(Filiere filiere) {
        Filiere savedFiliere = filiereRepository.save(filiere);
        return savedFiliere;
    }

	

}
