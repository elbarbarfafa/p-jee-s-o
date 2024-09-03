package fr.elbarbary.p_jee_s_o.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.elbarbary.p_jee_s_o.dtos.MedecinDto;

public interface IMedecinService {

	
	/**
	 * 
	 * @param nom : Nom du médecin recherché
	 * @param numeroMatricule : Numéro de matricule du médecin recherché
	 * @param pageable : Si nous devons naviguer autour de plusieurs résultats
	 * @return Une collection pageable de médecins
	 */
	public Page<MedecinDto> getAll(String nom, String numeroMatricule, Pageable pageable);
	
}
