package com.fst.walasso.walasso.model;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Parcours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String libelle;
	private String depart;
	private String arrive;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Filiere Filiere;
	


	
	//modification
		public boolean equals(Object other) {
			if ((this == other))
				return true;
			if ((other == null))
				return false;
			if (!(other instanceof Parcours))
				return false;
			Parcours castOther = (Parcours) other;

			return ((this.getId() == castOther.getId())
					|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())));
		}

		public int hashCode() {
			int result = 17;

			result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

			return result;
		}

}
