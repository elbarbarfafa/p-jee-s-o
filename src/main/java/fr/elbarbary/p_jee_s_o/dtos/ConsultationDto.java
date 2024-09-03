package fr.elbarbary.p_jee_s_o.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonNaming(SnakeCaseStrategy.class)
@Data
public class ConsultationDto {

	/**
	 * Numéro de la consultation
	 */
	private int numero;
	
	@NotBlank
	private LocalDateTime date;

	@NotBlank
	private String medecinMatricule;
	
	@NotBlank
	private String patientNumeroSecuriteSociale;
	
	@Nullable
	private Integer documentId;	
	
}
