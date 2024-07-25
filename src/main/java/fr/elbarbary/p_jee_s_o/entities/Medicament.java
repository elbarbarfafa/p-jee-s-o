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
public class Medicament {

	@Id
	private String code;
	
	@Column(length = 300)
	private String libelle;

	@OneToMany(mappedBy = "medicament")
	private Set<Prescription> prescriptions;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Prescription> getPrescriptions() {
		return Collections.unmodifiableSet(prescriptions);
	}
	
	public void addPrescription(Prescription prescription)
	{
		this.prescriptions.add(prescription);
		prescription.setMedicament(this);
	}

	
}