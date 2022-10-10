package com.fst.walasso.walasso.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fst.walasso.walasso.model.Domaine;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.fst.walasso.walasso.model.Mention;
import com.fst.walasso.walasso.service.DomaineService;
import com.fst.walasso.walasso.service.MentionService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data

public class MentionBean {

	private List<Mention> mentions;

	private Mention selectedMention;

	private List<Mention> selectedMentions;
	
	private List<Domaine> domaines;
	
	private DomaineService domaineService;

	@Autowired
	private MentionService mentionService;

	@PostConstruct
	public void init() {
		this.mentions = this.mentionService.getMentions();
		
	}

	public List<Mention> getMentions() {
		return mentions;
	}

	public Mention getSelectedMention() {
		return selectedMention;
	}

	public void setSelectedMention(Mention selectedMention) {
		this.selectedMention = selectedMention;
	}

	public List<Mention> getSelectedMentions() {
		return selectedMentions;
	}

	public void setSelectedMentions(List<Mention> selectedMentions) {
		this.selectedMentions = selectedMentions;
	}

	public void openNew() {
		this.selectedMention = new Mention();
	}

	public void saveMention() {
		if (this.selectedMention.getId() == null) {
			// this.selectedMention.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			// this.mentions.add(this.selectedMention);
			mentionService.saveMention(selectedMention);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mention Added"));
		} else {
			mentionService.saveMention(selectedMention);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mention Updated"));
		}
		mentions = mentionService.getMentions();
		PrimeFaces.current().executeScript("PF('manageMentionDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-mentions");
	}

	public void deleteMention() {
		// this.mentions.remove(this.selectedMention);
		// mentions = mentionService.getMentionRepository().findAll();
		this.mentionService.deleteMention(selectedMention.getId());
		// recharge
		mentions = mentionService.getMentionRepository().findAll();
		this.selectedMention = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mention supprimé"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-mentions");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedMentions()) {
			int size = this.selectedMentions.size();
			return size > 1 ? size + " mentions selected" : "1 mention selected";
		}

		return "Delete";
	}

	public boolean hasSelectedMentions() {
		return this.selectedMentions != null && !this.selectedMentions.isEmpty();
	}

	public void deleteSelectedMentions() {
		this.mentions.removeAll(this.selectedMentions);
		this.selectedMentions = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mentions Supprimé"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-mentions");
		PrimeFaces.current().executeScript("PF('dtMentions').clearFilters()");
	}

}
