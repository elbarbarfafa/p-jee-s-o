package fr.elbarbary.p_jee_s_o.entities;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	
	@Column(nullable = false)
	private LocalDateTime date;
	
	@ManyToOne(targetEntity = Medecin.class)
	private Medecin medecin;
	
	@ManyToOne(targetEntity = Patient.class)
	private Patient patient;
	
	@OneToMany(mappedBy = "")
	private Set<Prescription> prescriptions;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	private ConsultationDocument document;
	

	public void addPrescription(Prescription prescription)
	{
		this.prescriptions.add(prescription);
		prescription.setConsultation(this);
	}
	
	
}