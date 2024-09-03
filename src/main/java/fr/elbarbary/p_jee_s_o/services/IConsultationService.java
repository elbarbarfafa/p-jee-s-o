package fr.elbarbary.p_jee_s_o.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.exceptions.DateIsBeforeException;
import fr.elbarbary.p_jee_s_o.exceptions.DateIsNullException;
import fr.elbarbary.p_jee_s_o.exceptions.NotFoundException;

public interface IConsultationService {
	
	/**
	 * Ajoute une consultation postérieur à aujourd'hui
	 * @param dto : correspond à la consultation à ajouter. <b>Attention la date de consultation doit être postérieur à aujourd'hui</b>
	 * @return la consultation ajoutée
	 * @throws DateIsNullException : est levé si la date de la consultation n'est pas renseignée
	 * @throws DateIsBeforeException : est levé si la date est antérieur à aujourd'hui, ce qui ne répond pas au besoin
	 */
	public ConsultationDto add(ConsultationDto dto) throws DateIsNullException, DateIsBeforeException;
	
	/**
	 * Modifie une consultation déjà existante et programée
	 * @param id : Identifiant de la consultation à modifier
	 * @param dto : orrespond à la consultation à ajouter. <b>Attention la date de consultation doit être postérieur à aujourd'hui</b>
	 * @return la consultation modifiée
	 * @throws DateIsNullException : est levé si la date de la consultation n'est pas renseignée
	 * @throws DateIsBeforeException : est levé si la date est antérieur à aujourd'hui, ce qui ne répond pas au besoin
	 * @throws NotFoundException : est levé si la consultation est introuvable
	 */
	public ConsultationDto edit(int id, ConsultationDto dto) throws DateIsBeforeException, DateIsNullException, NotFoundException;
	
	/**
	 * Permet de supprimer une consultation dont sa date est postérieur
	 * @param id : Identifiant de la consultation à modifier
	 * @throws DateIsNullException : est levé si la date de la consultation n'est pas renseignée
	 * @throws DateIsBeforeException : est levé si la date est antérieur à aujourd'hui, ce qui ne répond pas au besoin
	 * @throws NotFoundException : est levé si la consultation est introuvable
	 */
	public void delete(int id) throws NotFoundException, DateIsBeforeException, DateIsNullException;
	
	/**
	 * Liste avec pagination l’ensemble des consultations d’un patient donné
	 * @param numSecu
	 * @param pageable
	 * @return
	 */
	public Page<ConsultationDto> findByPatientNumeroSecurite(String numSecu, Pageable pageable);
	
	public ConsultationDto setDocument(Integer numero, MultipartFile file);
	
}
