package com.fst.walasso.walasso.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fst.walasso.walasso.model.Grade;
import com.fst.walasso.walasso.model.TypeSysteme;
import com.fst.walasso.walasso.service.GradeService;
import com.fst.walasso.walasso.service.TypeSystemeService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data


public class GradeBean {

	private List<Grade> grades;

	private Grade selectedGrade;

	private List<Grade> selectedGrades;
    
	private List<TypeSysteme> typeSysteme;
	
	private TypeSystemeService typeSystemeService;
	
	
	@Autowired
	private GradeService gradeService;

	@PostConstruct
	public void init() {
		//this.grades = this.gradeService.getGradeRepository().findAll();
		this.grades = this.gradeService.getGrades();
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public Grade getSelectedGrade() {
		return selectedGrade;
	}

	public void setSelectedGrade(Grade selectedGrade) {
		this.selectedGrade = selectedGrade;
	}

	public List<Grade> getSelectedGrades() {
		return selectedGrades;
	}

	public void setSelectedGrades(List<Grade> selectedGrades) {
		this.selectedGrades = selectedGrades;
	}

	public void openNew() {
		this.selectedGrade = new Grade();
	}

	public void saveGrade() {
		if (this.selectedGrade.getId() == null) {
			// this.selectedGrade.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			// this.grades.add(this.selectedGrade);
			gradeService.saveGrade(selectedGrade);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grade Added"));
		} else {
			gradeService.saveGrade(selectedGrade);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grade Updated"));
		}
		grades = gradeService.getGrades();
		PrimeFaces.current().executeScript("PF('manageGradeDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-grades");
	}

	public void deleteGrade() {
		// this.grades.remove(this.selectedGrade);
		// grades = gradeService.getGradeRepository().findAll();
		this.gradeService.deleteGrade(selectedGrade.getId());
		// recharge
		grades = gradeService.getGradeRepository().findAll();
		this.selectedGrade = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grade supprimé"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-grades");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedGrades()) {
			int size = this.selectedGrades.size();
			return size > 1 ? size + " grades selected" : "1 grade selected";
		}

		return "Delete";
	}

	public boolean hasSelectedGrades() {
		return this.selectedGrades != null && !this.selectedGrades.isEmpty();
	}

	public void deleteSelectedGrades() {
		this.grades.removeAll(this.selectedGrades);
		this.selectedGrades = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grades Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-grades");
		PrimeFaces.current().executeScript("PF('dtGrades').clearFilters()");
	}


}
