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


import com.fst.walasso.walasso.model.Filiere;
import com.fst.walasso.walasso.model.Grade;
import com.fst.walasso.walasso.model.Mention;
import com.fst.walasso.walasso.service.FiliereService;
import com.fst.walasso.walasso.service.GradeService;
import com.fst.walasso.walasso.service.MentionService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data

public class FiliereBean {
	
	private List<Filiere> filieres;

	private Filiere selectedFiliere;

	private List<Filiere> selectedFilieres;
	
	private List<Mention> mentions;
	
	private  MentionService mentionService;
	
	private List<Grade> grades;
	
	private GradeService gradeService;

	@Autowired
	private FiliereService filiereService;

	@PostConstruct
	public void init() {
		//this.filieres = this.filiereService.getFiliereRepository().findAll();
		this.filieres  = this.filiereService.getFilieres();
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public Filiere getSelectedFiliere() {
		return selectedFiliere;
	}

	public void setSelectedFiliere(Filiere selectedFiliere) {
		this.selectedFiliere = selectedFiliere;
	}

	public List<Filiere> getSelectedFilieres() {
		return selectedFilieres;
	}

	public void setSelectedFilieres(List<Filiere> selectedFilieres) {
		this.selectedFilieres = selectedFilieres;
	}

	public void openNew() {
		this.selectedFiliere = new Filiere();
	}

	public void saveFiliere() {
		if (this.selectedFiliere.getId() == null) {
			// this.selectedFiliere.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			// this.filieres.add(this.selectedFiliere);
			filiereService.saveFiliere(selectedFiliere);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Filiere ajoutée"));
		} else {
			filiereService.saveFiliere(selectedFiliere);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mise en jour effectuée"));
		}
		filieres = filiereService.getFilieres();
		PrimeFaces.current().executeScript("PF('manageFiliereDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-filieres");
	}

	public void deleteFiliere() {
		// this.filieres.remove(this.selectedFiliere);
		// filieres = filiereService.getFiliereRepository().findAll();
		this.filiereService.deleteFiliere(selectedFiliere.getId());
		// recharge
		filieres = filiereService.getFiliereRepository().findAll();
		this.selectedFiliere = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Filiere supprimé"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-filieres");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedFilieres()) {
			int size = this.selectedFilieres.size();
			return size > 1 ? size + " filieres selected" : "1 filiere selected";
		}

		return "Delete";
	}

	public boolean hasSelectedFilieres() {
		return this.selectedFilieres != null && !this.selectedFilieres.isEmpty();
	}

	public void deleteSelectedFilieres() {
		this.filieres.removeAll(this.selectedFilieres);
		this.selectedFilieres = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Filieres supprimées"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-filieres");
		PrimeFaces.current().executeScript("PF('dtFilieres').clearFilters()");
	}


	

}
