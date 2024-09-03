package fr.elbarbary.p_jee_s_o.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.elbarbary.p_jee_s_o.dtos.MedecinDto;
import fr.elbarbary.p_jee_s_o.mappers.MedecinMapperImpl;
import fr.elbarbary.p_jee_s_o.repositories.MedecinRepository;

@Service
public class MedecinServiceImpl implements IMedecinService {

protected MedecinRepository repository;
	
	protected MedecinMapperImpl medecinMapper;
	
	public MedecinServiceImpl(MedecinRepository repository) {
		this.repository = repository;
		this.medecinMapper = new MedecinMapperImpl();
	}

	@Override
	public Page<MedecinDto> getAll(String nom, String numeroMatricule, Pageable pageable){
		Page<MedecinDto> result = null;
		if((nom == null || nom.isEmpty()) && (numeroMatricule == null || numeroMatricule.isEmpty())) {
			// Si les deux options de recherches ne sont pas renseigné
			result = repository.findAll(pageable).map(e-> medecinMapper.mapToDto(e));
		} else {
			// Dans le cas où le nom ou le numéro de matricule est renseigné
			result = repository.findByNomOrMatricule(nom, numeroMatricule, pageable).map(e->medecinMapper.mapToDto(e));
		}
		return result;
	}

}
