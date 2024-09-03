package fr.elbarbary.p_jee_s_o.mappers;

import org.springframework.stereotype.Component;

import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.entities.Consultation;

@Component
public class ConsultationMapperImpl extends Mapper<Consultation, ConsultationDto> {
	
	public ConsultationMapperImpl() {
		super(Consultation.class, ConsultationDto.class);
	}
		
}
