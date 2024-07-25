package fr.elbarbary.p_jee_s_o.entities;

import java.util.Collections;
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
	
	@OneToMany(mappedBy = "patient")
	private Set<Consultation> consultations;

	public String getNumeroSS() {
		return numeroSS;
	}

	public void setNumeroSS(String numeroSS) {
		this.numeroSS = numeroSS;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Consultation> getConsultations() {
		return Collections.unmodifiableSet(consultations);
	}
	
	public void addConsultation(Consultation consultation)
	{
		this.consultations.add(consultation);
		consultation.setPatient(this);
	}
	
}
