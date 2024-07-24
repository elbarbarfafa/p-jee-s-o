package fr.elbarbary.p_jee_s_o.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Patient {

	@Id
	@Column(length = 15)
	private String numeroSS;
	
	@Column(length=150)
	private String nom;
	
	@OneToMany()
	private Set<Consultation> consultations;
	
	
}
