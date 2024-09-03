package fr.elbarbary.p_jee_s_o.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Patient {

	@Id
	@Column(length = 15)
	private String numeroSecuriteSociale;
	
	@Column(length=150)
	private String nom;
	
	@OneToMany(mappedBy = "patient")
	private Set<Consultation> consultations;
	
	public void addConsultation(Consultation consultation)
	{
		this.consultations.add(consultation);
		consultation.setPatient(this);
	}
	
}
