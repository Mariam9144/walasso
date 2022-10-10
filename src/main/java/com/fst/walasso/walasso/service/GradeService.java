package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.Grade;
import com.fst.walasso.walasso.repository.GradeRepository;

import lombok.Data;

@Data
@Service

public class GradeService {
	@Autowired
	private GradeRepository gradeRepository;

	public Optional<Grade> getGrade(final long id) {
        return gradeRepository.findById(id);
    }

    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    public void deleteGrade(final long id) {
    	gradeRepository.deleteById(id);
    }

    public Grade saveGrade(Grade grade) {
        Grade savedGrade = gradeRepository.save(grade);
        return savedGrade;
    }

	

}
