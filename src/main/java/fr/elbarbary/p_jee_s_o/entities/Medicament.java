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
public class Medicament {

	@Id
	private String code;
	
	@Column(length = 300)
	private String libelle;

	@OneToMany(mappedBy = "medicament")
	private Set<Prescription> prescriptions;
	
	public void addPrescription(Prescription prescription)
	{
		this.prescriptions.add(prescription);
		prescription.setMedicament(this);
	}
	
}