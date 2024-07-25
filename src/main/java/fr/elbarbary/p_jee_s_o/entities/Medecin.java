package fr.elbarbary.p_jee_s_o.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Medecin {

	@Id
	private String matricule;
	
	@Column(length = 150)
	private String nom;
	
	@OneToMany(mappedBy = "medecin")
	private Set<Consultation> consultations;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}
	
	public void addConsultation(Consultation consultation)
	{
		this.consultations.add(consultation);
		consultation.setMedecin(this);
	}
	
	
	
}