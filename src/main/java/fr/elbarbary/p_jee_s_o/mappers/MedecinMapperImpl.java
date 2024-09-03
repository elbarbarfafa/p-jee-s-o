package fr.elbarbary.p_jee_s_o.mappers;

import org.springframework.stereotype.Component;

import fr.elbarbary.p_jee_s_o.dtos.MedecinDto;
import fr.elbarbary.p_jee_s_o.entities.Medecin;

@Component
public class MedecinMapperImpl extends Mapper<Medecin, MedecinDto> {
	
	public MedecinMapperImpl() {
		super(Medecin.class, MedecinDto.class);
	}
	
}
