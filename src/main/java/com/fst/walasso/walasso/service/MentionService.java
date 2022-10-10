package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.Mention;
import com.fst.walasso.walasso.repository.MentionRepository;

import lombok.Data;

@Data
@Service

public class MentionService {
	@Autowired
	private MentionRepository mentionRepository;

    public Optional<Mention> getMention(final long id) {
        return mentionRepository.findById(id);
    }

    public List<Mention> getMentions() {
        return mentionRepository.findAll();
    }

    public void deleteMention(final long id) {
    	mentionRepository.deleteById(id);
    }

    public Mention saveMention(Mention mention) {
        Mention savedMention = mentionRepository.save(mention);
        return savedMention;
    }

	

}
