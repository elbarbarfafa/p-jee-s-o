package fr.elbarbary.p_jee_s_o.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	
	
}