package fr.elbarbary.p_jee_s_o.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Medicament {

	@Id
	private String code;
	
	@Column(length = 300)
	private String libelle;

	
}