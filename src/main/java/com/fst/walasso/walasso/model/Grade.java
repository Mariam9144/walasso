package com.fst.walasso.walasso.model;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String code;
	private String libelle;
	private String description;
	@Column(name = "nb_annee")
	private String nbAnnee;
	/*
	@ManyToOne
	@JoinColumn(name = "filiere" )
	private Filiere filiere;
	*/
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
	private TypeSysteme typeSysteme;
	

	//modification
			public boolean equals(Object other) {
				if ((this == other))
					return true;
				if ((other == null))
					return false;
				if (!(other instanceof Grade))
					return false;
				Grade castOther = (Grade) other;

				return ((this.getId() == castOther.getId())
						|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())));
			}

			public int hashCode() {
				int result = 17;

				result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

				return result;
			}
}
