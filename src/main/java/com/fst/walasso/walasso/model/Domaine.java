package com.fst.walasso.walasso.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Domaine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String libelle;
	private String description;
	
	
	//modification
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Domaine))
			return false;
		Domaine castOther = (Domaine) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

		return result;
	}


	
}

