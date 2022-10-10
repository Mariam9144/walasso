package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.Parcours;
import com.fst.walasso.walasso.repository.ParcoursRepository;

import lombok.Data;

@Data
@Service

public class ParcoursService {
	@Autowired
	private ParcoursRepository parcoursRepository;

    public Optional<Parcours> getParcours(final long id) {
        return parcoursRepository.findById(id);
    }

    public List<Parcours> getParcourss() {
        return parcoursRepository.findAll();
    }

    public void deleteParcours(final long id) {
    	parcoursRepository.deleteById(id);
    }

    public Parcours saveParcours(Parcours parcours) {
        Parcours savedParcours = parcoursRepository.save(parcours);
        return savedParcours;
    }


	

}
