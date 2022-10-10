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


import com.fst.walasso.walasso.model.TypeSysteme;
import com.fst.walasso.walasso.service.TypeSystemeService;

import lombok.Data;
@Component
@ManagedBean
@SessionScoped
@Data

public class TypeSystemeBean {

	private List<TypeSysteme> typeSystemes;

	private TypeSysteme selectedTypeSysteme;

	private List<TypeSysteme> selectedTypeSystemes;

	@Autowired
	private TypeSystemeService typeSystemeService;

	@PostConstruct
	public void init() {
		this.typeSystemes = this.typeSystemeService.getTypeSystemeRepository().findAll();
	}

	public List<TypeSysteme> getTypeSystemes() {
		return typeSystemes;
	}

	public TypeSysteme getSelectedTypeSysteme() {
		return selectedTypeSysteme;
	}

	public void setSelectedTypeSysteme(TypeSysteme selectedTypeSysteme) {
		this.selectedTypeSysteme = selectedTypeSysteme;
	}

	public List<TypeSysteme> getSelectedTypeSystemes() {
		return selectedTypeSystemes;
	}

	public void setSelectedTypeSystemes(List<TypeSysteme> selectedTypeSystemes) {
		this.selectedTypeSystemes = selectedTypeSystemes;
	}

	public void openNew() {
		this.selectedTypeSysteme = new TypeSysteme();
	}

	public void saveTypeSysteme() {
		if (this.selectedTypeSysteme.getId() == null) {
			// this.selectedTypeSysteme.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			// this.typeSystemes.add(this.selectedTypeSysteme);
			typeSystemeService.saveTypeSysteme(selectedTypeSysteme);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TypeSysteme Added"));
		} else {
			typeSystemeService.saveTypeSysteme(selectedTypeSysteme);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TypeSysteme Updated"));
		}
		typeSystemes = typeSystemeService.getTypeSystemes();
		PrimeFaces.current().executeScript("PF('manageTypeSystemeDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-typeSystemes");
	}

	public void deleteTypeSysteme() {
		// this.typeSystemes.remove(this.selectedTypeSysteme);
		// typeSystemes = typeSystemeService.getTypeSystemeRepository().findAll();
		this.typeSystemeService.deleteTypeSysteme(selectedTypeSysteme.getId());
		// recharge
		typeSystemes = typeSystemeService.getTypeSystemeRepository().findAll();
		this.selectedTypeSysteme = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TypeSysteme supprimé"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-typeSystemes");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedTypeSystemes()) {
			int size = this.selectedTypeSystemes.size();
			return size > 1 ? size + " typeSystemes selected" : "1 typeSysteme selected";
		}

		return "Delete";
	}

	public boolean hasSelectedTypeSystemes() {
		return this.selectedTypeSystemes != null && !this.selectedTypeSystemes.isEmpty();
	}

	public void deleteSelectedTypeSystemes() {
		this.typeSystemes.removeAll(this.selectedTypeSystemes);
		this.selectedTypeSystemes = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TypeSystemes Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-typeSystemes");
		PrimeFaces.current().executeScript("PF('dtTypeSystemes').clearFilters()");
	}


}
