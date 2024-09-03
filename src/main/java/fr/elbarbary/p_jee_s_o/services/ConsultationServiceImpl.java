package fr.elbarbary.p_jee_s_o.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.entities.Consultation;
import fr.elbarbary.p_jee_s_o.entities.ConsultationDocument;
import fr.elbarbary.p_jee_s_o.exceptions.DateIsBeforeException;
import fr.elbarbary.p_jee_s_o.exceptions.DateIsNullException;
import fr.elbarbary.p_jee_s_o.exceptions.NotFoundException;
import fr.elbarbary.p_jee_s_o.mappers.ConsultationMapperImpl;
import fr.elbarbary.p_jee_s_o.repositories.ConsultationRepository;
import fr.elbarbary.p_jee_s_o.storage.StorageService;

@Service
public class ConsultationServiceImpl implements ConsultationService {

	private final ConsultationRepository consultationRepository;
	
	private final ConsultationMapperImpl consultationMapper;
	
	private final StorageService storageService;
	
	public ConsultationServiceImpl(ConsultationRepository consultationRepository, ConsultationMapperImpl consultationMapper, StorageService storageService)
	{
		this.consultationRepository = consultationRepository;
		this.consultationMapper = consultationMapper;
		this.storageService = storageService;
	}
	
	public ConsultationDto add(ConsultationDto dto) throws DateIsNullException, DateIsBeforeException {
		if(dto.getDate() == null) {
			throw new DateIsNullException();
		}
		if(dto.getDate().isBefore(LocalDateTime.now())) {
			throw new DateIsBeforeException();
		}
		Consultation consultation = this.consultationMapper.mapToEntity(dto);
		return this.consultationMapper.mapToDto(this.consultationRepository.save(consultation));
	}
	

	public ConsultationDto edit(int id, ConsultationDto dto) throws DateIsBeforeException, DateIsNullException, NotFoundException {
		if(dto.getDate() == null) {
			throw new DateIsNullException();
		}
		if(dto.getDate().isBefore(LocalDateTime.now())) {
			throw new DateIsBeforeException();
		}
		if(!this.consultationRepository.existsById(id)) throw new NotFoundException(); // S'il n'existe pas, on ne modifie pas et on throw une exception pour remonter l'information
		Consultation elementEntity = consultationMapper.mapToEntity(dto);
		elementEntity.setNumero(id);
		elementEntity = this.consultationRepository.save(elementEntity);
		return consultationMapper.mapToDto(elementEntity);
	}
	

	public void delete(int id) throws NotFoundException, DateIsBeforeException, DateIsNullException
	{
		Optional<Consultation> consultationEntity = this.consultationRepository.findById(id);
		if(consultationEntity.isEmpty()) throw new NotFoundException();
		if(consultationEntity.get().getDate() == null) {
			throw new DateIsNullException();
		}
		if(consultationEntity.get().getDate().isBefore(LocalDateTime.now())) {
			throw new DateIsBeforeException();
		}
		this.consultationRepository.delete(consultationEntity.get());
	}
	

	public Page<ConsultationDto> findByPatientNumeroSecurite(String numSecu, Pageable pageable) {
		return this.consultationRepository.findByPatientNumeroSecuriteSociale(numSecu,pageable).map(consultationMapper::mapToDto);
	}

	@Override
	public ConsultationDto setDocument(Integer numero, MultipartFile file) {
		Optional<Consultation> consultation = consultationRepository.findById(numero);
		if(consultation.isPresent())
		{
			String path = storageService.store(file); // On récupère le chemin du fichier une fois stocké
			// Si la consultation existe nous lui associons le chemin du fichié stocké
			Consultation consul = consultation.get();
			ConsultationDocument doc = new ConsultationDocument();
			doc.setPath(path);
			consul.setDocument(doc);
			doc.setConsultation(consul);
			consul = consultationRepository.save(consul); // On sauvegarde la consultation une fois le document associé
			return consultationMapper.mapToDto(consul);
		}
		return null;
	}
	
}
