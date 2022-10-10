package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.TypeSysteme;
import com.fst.walasso.walasso.repository.TypeSystemeRepository;

import lombok.Data;

@Data
@Service

public class TypeSystemeService {
	
	@Autowired
	private TypeSystemeRepository typeSystemeRepository;

    public Optional<TypeSysteme> getTypeSysteme(final long id) {
        return typeSystemeRepository.findById(id);
    }

    public List<TypeSysteme> getTypeSystemes() {
        return typeSystemeRepository.findAll();
    }

    public void deleteTypeSysteme(final long id) {
    	typeSystemeRepository.deleteById(id);
    }

    public TypeSysteme saveTypeSysteme(TypeSysteme typeSysteme) {
        TypeSysteme savedTypeSysteme = typeSystemeRepository.save(typeSysteme);
        return savedTypeSysteme;
    }


	

}
