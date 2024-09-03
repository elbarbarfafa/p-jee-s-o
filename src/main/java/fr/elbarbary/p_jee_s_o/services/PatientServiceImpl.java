package fr.elbarbary.p_jee_s_o.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.elbarbary.p_jee_s_o.dtos.PatientDto;
import fr.elbarbary.p_jee_s_o.mappers.PatientMapperImpl;
import fr.elbarbary.p_jee_s_o.repositories.ConsultationRepository;
import fr.elbarbary.p_jee_s_o.repositories.PatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {
	
	protected PatientRepository repository;
	protected PatientMapperImpl patientMapper;
	protected ConsultationRepository consultationRepository;
	
	public PatientServiceImpl(PatientRepository repository, ConsultationRepository consRepository) {
		this.repository = repository;
		patientMapper = new PatientMapperImpl();
		this.consultationRepository = consRepository;
	}
	
	@Override
	public Page<PatientDto> getAll(String nom, String numeroSecu, Pageable pageable){
		Page<PatientDto> result = null;
		if((nom == null || nom.isEmpty()) || (numeroSecu == null || numeroSecu.isEmpty())) {
			// Si les deux options de recherches ne sont pas renseigné
			result = repository.findAll(pageable).map(e-> patientMapper.map(e));
		} else {
			// Dans le cas où le nom ou le numéro de sécurité est renseigné
			result = repository.findByNomOrNumeroSecuriteSociale(nom, numeroSecu, pageable).map(e->patientMapper.map(e));
		}
		return result;
	}

}
