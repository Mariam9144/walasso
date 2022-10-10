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

import com.fst.walasso.walasso.model.Domaine;
import com.fst.walasso.walasso.repository.DomaineRepository;
import com.fst.walasso.walasso.service.DomaineService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class DomaineBean {
	
	private List<Domaine> domaines;

    private Domaine selectedDomaine;

    private List<Domaine> selectedDomaines;

    @Autowired
    private DomaineService domaineService;
    
    @Autowired
    private DomaineRepository domaineRepository;

    @PostConstruct
    public void init() {
        this.domaines = this.domaineService.getDomaineRepository().findAll();
    }

    public List<Domaine> getDomaines() {
        return domaines;
    }

    public Domaine getSelectedDomaine() {
        return selectedDomaine;
    }

    public void setSelectedDomaine(Domaine selectedDomaine) {
        this.selectedDomaine = selectedDomaine;
    }

    public List<Domaine> getSelectedDomaines() {
        return selectedDomaines;
    }

    public void setSelectedDomaines(List<Domaine> selectedDomaines) {
        this.selectedDomaines = selectedDomaines;
    }

    public void openNew() {
        this.selectedDomaine = new Domaine();
    }

    public void saveDomaine() {
        if (this.selectedDomaine.getId() == null) {
           // this.selectedDomaine.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            //this.domaines.add(this.selectedDomaine);
        	domaineService.saveDomaine(selectedDomaine);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Domaine ajouté"));
        }
        else {
        	domaineService.saveDomaine(selectedDomaine);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" Le Domaine est mis a jour"));
        }
        domaines = domaineService.getDomaines();
        PrimeFaces.current().executeScript("PF('manageDomaineDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-domaines");
    }

    public void deleteDomaine() {
        //this.domaines.remove(this.selectedDomaine);
    	//domaines = domaineService.getDomaineRepository().findAll();
    	this.domaineService.deleteDomaine(selectedDomaine.getId());
    	//recharge
    	domaines = domaineService.getDomaineRepository().findAll();
        this.selectedDomaine = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Domaine supprimé"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-domaines");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedDomaines()) {
            int size = this.selectedDomaines.size();
            return size > 1 ? size + " domaines selected" : "1 domaine selected";
        }

        return "Delete";
    }

    public boolean hasSelectedDomaines() {
        return this.selectedDomaines != null && !this.selectedDomaines.isEmpty();
    }

    public void deleteSelectedDomaines() {
        this.domaines.removeAll(this.selectedDomaines);
        this.domaineRepository.deleteAll(this.selectedDomaines);
        this.selectedDomaines = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Domaines Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-domaines");
        PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");
    }

}
