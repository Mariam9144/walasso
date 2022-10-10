package com.fst.walasso.walasso.model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity

public class Filiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String code;
	private String intitule;
	private String description;
	private String niveauDepart;
	private String niveauArrive;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Mention mention;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Grade grade;
	
	/*
	@OneToMany(mappedBy = "filiere", fetch =FetchType.LAZY)
	private Collection<Mention> Mention;
	
	@ManyToOne
	@JoinColumn(name = "parcours")
	private Parcours parcours;
	
	@OneToMany(mappedBy = "filiere", fetch =FetchType.LAZY)
	private Collection<Grade> Grade;
   
	*/
	
	//modification
		public boolean equals(Object other) {
			if ((this == other))
				return true;
			if ((other == null))
				return false;
			if (!(other instanceof Filiere))
				return false;
			Filiere castOther = (Filiere) other;

			return ((this.getId() == castOther.getId())
					|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())));
		}

		public int hashCode() {
			int result = 17;

			result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

			return result;
		}

}
