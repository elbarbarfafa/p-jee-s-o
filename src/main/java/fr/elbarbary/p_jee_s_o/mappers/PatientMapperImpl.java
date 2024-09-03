package fr.elbarbary.p_jee_s_o.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import fr.elbarbary.p_jee_s_o.dtos.PatientDto;
import fr.elbarbary.p_jee_s_o.entities.Patient;

@Component
public class PatientMapperImpl {

	private ModelMapper mapper;
	
	public PatientMapperImpl() {
		this.mapper = new ModelMapper();
	}
	
	public PatientDto map(Patient patient) {
		return this.mapper.map(patient, PatientDto.class);
	}
	
	public Patient map(PatientDto patientDto) {
		return this.mapper.map(patientDto, Patient.class);
	}
	
}