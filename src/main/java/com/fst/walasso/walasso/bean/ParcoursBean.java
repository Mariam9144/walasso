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
import com.fst.walasso.walasso.model.Parcours;
import com.fst.walasso.walasso.service.FiliereService;
import com.fst.walasso.walasso.service.ParcoursService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data

public class ParcoursBean {

	private List<Parcours> parcourss;

	private Parcours selectedParcours;

	private List<Parcours> selectedParcourss;
	
	private List<Filiere> filiere;
	
	private FiliereService filiereService;

	@Autowired
	private ParcoursService parcoursService;

	@PostConstruct
	public void init() {
		//this.parcourss = this.parcoursService.getParcoursRepository().findAll();
		this.parcourss  = this.parcoursService.getParcourss();
	}

	public List<Parcours> getParcourss() {
		return parcourss;
	}

	public Parcours getSelectedParcours() {
		return selectedParcours;
	}

	public void setSelectedParcours(Parcours selectedParcours) {
		this.selectedParcours = selectedParcours;
	}

	public List<Parcours> getSelectedParcourss() {
		return selectedParcourss;
	}

	public void setSelectedParcourss(List<Parcours> selectedParcourss) {
		this.selectedParcourss = selectedParcourss;
	}

	public void openNew() {
		this.selectedParcours = new Parcours();
	}

	public void saveParcours() {
		if (this.selectedParcours.getId() == null) {
			// this.selectedParcours.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			// this.parcourss.add(this.selectedParcours);
			parcoursService.saveParcours(selectedParcours);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcours Added"));
		} else {
			parcoursService.saveParcours(selectedParcours);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcours Updated"));
		}
		parcourss = parcoursService.getParcourss();
		PrimeFaces.current().executeScript("PF('manageParcoursDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-parcourss");
	}

	public void deleteParcours() {
		// this.parcourss.remove(this.selectedParcours);
		// parcourss = parcoursService.getParcoursRepository().findAll();
		this.parcoursService.deleteParcours(selectedParcours.getId());
		// recharge
		parcourss = parcoursService.getParcoursRepository().findAll();
		this.selectedParcours = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcours supprimé"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-parcourss");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedParcourss()) {
			int size = this.selectedParcourss.size();
			return size > 1 ? size + " parcourss selected" : "1 parcours selected";
		}

		return "Delete";
	}

	public boolean hasSelectedParcourss() {
		return this.selectedParcourss != null && !this.selectedParcourss.isEmpty();
	}

	public void deleteSelectedParcourss() {
		this.parcourss.removeAll(this.selectedParcourss);
		this.selectedParcourss = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parcourss Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-parcourss");
		PrimeFaces.current().executeScript("PF('dtParcourss').clearFilters()");
	}


}
