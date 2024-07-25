package fr.elbarbary.p_jee_s_o.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table
public class Prescription {

	@EmbeddedId
	PrescriptionKey id;
	
	@ManyToOne
	@MapsId("medicamentCode")
	@JoinColumn(name="medicament_code")
	private Medicament medicament;
	
	@ManyToOne
	@MapsId("consultationNumero")
	@JoinColumn(name="consultation_numero")
	private Consultation consultation;
	
	/**
	 * Nombre de prises
	 */
	@Column
	private int prises;

	public PrescriptionKey getId() {
		return id;
	}

	public void setId(PrescriptionKey id) {
		this.id = id;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
		medicament.addPrescription(this);
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public int getPrises() {
		return prises;
	}

	public void setPrises(int prises) {
		this.prises = prises;
	}
	
	
}
