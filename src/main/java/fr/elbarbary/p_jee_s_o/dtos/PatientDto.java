package fr.elbarbary.p_jee_s_o.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(SnakeCaseStrategy.class)
@Data
public class PatientDto {

	private String numeroSecuriteSociale;
	
	private String nom;
	
	
}
