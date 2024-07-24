package fr.elbarbary.p_jee_s_o.entities;

import jakarta.persistence.Column;

public class PrescriptionKey {

	@Column(name = "medicament_code")
	String medicamentCode;
	
	@Column(name="consultation_numero")
	int consultationNumero;
	
	
}