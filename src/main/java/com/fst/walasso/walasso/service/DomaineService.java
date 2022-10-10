package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.Domaine;
import com.fst.walasso.walasso.repository.DomaineRepository;

import lombok.Data;

@Data
@Service
public class DomaineService {
	
	@Autowired
    private DomaineRepository domaineRepository;

    public Optional<Domaine> getDomaine(final int id) {
        return domaineRepository.findById(id);
    }

    public List<Domaine> getDomaines() {
        return domaineRepository.findAll();
    }

    public void deleteDomaine(final int id) {
    	domaineRepository.deleteById(id);
    }

    public Domaine saveDomaine(Domaine domaine) {
        Domaine savedDomaine = domaineRepository.save(domaine);
        return savedDomaine;
    }


}
