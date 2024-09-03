package fr.elbarbary.p_jee_s_o.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.elbarbary.p_jee_s_o.dtos.MedecinDto;
import fr.elbarbary.p_jee_s_o.services.IMedecinService;

/**
 * Contrôleur déclarant les routes disponibles pour les médecins
 */
@RestController
@RequestMapping(path = "/api/medecins")
public class MedecinController {

	protected IMedecinService service;
	
	public MedecinController(IMedecinService medecinService)
	{
		this.service = medecinService;
	}
	
	/**
	 * Cette méthode retourne l'ensemble des médecins sous forme de pagination
	 * @param nom : Rechercher un ou des médecins ayant le nom spécifié. <i>optionnel</i>
	 * @param matricule : Rechercher un ou des médecins ayant le numéro de matricule renseigné. <i>optionnel</i>
	 * @param pageable : S'il est nécessaire de naviguer entre les pages
	 * @return Une collection paginable des médecins
	 */
	@GetMapping
	public Page<MedecinDto> all(@RequestParam(name="nom", required = false) String nom, @RequestParam(name="matricule",required=false) String matricule, @ParameterObject Pageable pageable){
		return service.getAll(nom, matricule, pageable);
	}
	
}
