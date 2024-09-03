package fr.elbarbary.p_jee_s_o.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.elbarbary.p_jee_s_o.dtos.PatientDto;


public interface IPatientService {
	
	/**
	 * Récupère l'ensemble des patients
	 * @param nom : Nom du patient recherché
	 * @param numeroSecu : Numéro de sécurité sociale recherché
	 * @param pageable : Si nous devons naviguer sur plusieurs pages
	 * @return Une collection pageable de patients
	 */
	public Page<PatientDto> getAll(String nom, String numeroSecu, Pageable pageable);
}