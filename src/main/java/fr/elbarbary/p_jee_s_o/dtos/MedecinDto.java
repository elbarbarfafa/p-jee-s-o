package fr.elbarbary.p_jee_s_o.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import fr.elbarbary.p_jee_s_o.entities.Consultation;
import fr.elbarbary.p_jee_s_o.entities.ConsultationDocument;
import fr.elbarbary.p_jee_s_o.entities.Medecin;
import fr.elbarbary.p_jee_s_o.entities.Patient;
import fr.elbarbary.p_jee_s_o.entities.Prescription;
import lombok.Data;

@JsonNaming(SnakeCaseStrategy.class)
@Data
public class MedecinDto {

	private String matricule;
	
	private String nom;
	
}
