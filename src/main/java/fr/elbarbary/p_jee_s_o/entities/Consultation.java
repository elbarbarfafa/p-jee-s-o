package fr.elbarbary.p_jee_s_o.entities;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	
	@Column
	private LocalDateTime date;
	
	@ManyToOne(targetEntity = Medecin.class)
	private Medecin medecin;
	
	@ManyToOne(targetEntity = Patient.class)
	private Patient patient;
	
	@OneToMany(mappedBy = "")
	private Set<Prescription> prescriptions;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<Prescription> getPrescriptions() {
		return Collections.unmodifiableSet(prescriptions);
	}

	public void addPrescription(Prescription prescription)
	{
		this.prescriptions.add(prescription);
		prescription.setConsultation(this);
	}
	
	
	
}