package fr.elbarbary.p_jee_s_o.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PrescriptionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6212922521949457631L;

	@Column(name = "medicament_code")
	String medicamentCode;
	
	@Column(name="consultation_numero")
	int consultationNumero;	
	
}