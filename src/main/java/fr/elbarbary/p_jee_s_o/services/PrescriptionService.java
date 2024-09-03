package fr.elbarbary.p_jee_s_o.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.elbarbary.p_jee_s_o.dtos.PrescriptionDetailDto;

public interface PrescriptionService {
	
	/**
	 * Détailler les médicaments prescrits dans une consultation
	 * @param numero : Numéro de la consultation
	 * @param pageable : Pagination à appliquer sur les prescriptions
	 * @return Page
	 */
	public Page<PrescriptionDetailDto> findMedicamentsByConsultationNumero(final int numero, Pageable pageable);
	
	/**
	 * A partir d'une liste de prescriptions, cette méthode remplace l'ensemble des prescriptions d'une consultation par celle fournie
	 * @param numero de la consultation
	 * @param detailsDto Liste des prescriptions nouvelle à attribuer à cette consultation
	 * @return La liste des prescriptions attribuer à cette consultation
	 */
	public List<PrescriptionDetailDto> replacePrescriptionsByConsultationNumero(final int numero, List<PrescriptionDetailDto> detailsDto);
}
