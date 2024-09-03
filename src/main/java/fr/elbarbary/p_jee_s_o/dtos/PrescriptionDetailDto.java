package fr.elbarbary.p_jee_s_o.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(SnakeCaseStrategy.class)
@Data
public class PrescriptionDetailDto {

	private Integer numeroConsultation;
	
	private String medicamentCode;
	
	private String medicamentLibelle;
	
	private Integer nombrePrises;
	
}
