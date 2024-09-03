package fr.elbarbary.p_jee_s_o.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.dtos.PatientDto;
import fr.elbarbary.p_jee_s_o.services.IConsultationService;
import fr.elbarbary.p_jee_s_o.services.IPatientService;

/**
 * Contrôleur déclarant les routes disponibles pour les patients
 */
@RestController
@RequestMapping(path = "/api/patients")
public class PatientController {

	protected IPatientService patientService;
	protected IConsultationService consultationService;
	
	public PatientController(IPatientService patientService, IConsultationService consultationService) {
		this.patientService = patientService;
		this.consultationService = consultationService;
	}
	

	/**
	 * Récupère l'ensemble des consultations d'un patient
	 * @param secu : Numéro de sécurité social du patient
	 * @param pageable : Objet de pagination nécessaire pour naviguer entre les pages.
	 * @return Une pagination du résultat
	 */
	@GetMapping(path = "/{num-secu}/consultations")
	public Page<ConsultationDto> getConsultations(@PathVariable(name = "num-secu", required = true) String secu, @ParameterObject Pageable pageable) {
		return consultationService.findByPatientNumeroSecurite(secu, pageable);
	}
	
	/**
	 * Récupère l'ensemble des patients
	 * @param nom : Rechercher un ou des patients ayant le nom spécifié. <i>optionnel</i>
	 * @param numSecu : Rechercher un ou des patients ayant le numéro de sécurité sociale renseigné. <i>optionnel</i>
	 * @param pageable : S'il est nécessaire de naviguer entre les pages
	 * @return Une collection paginable des patients
	 */
	@GetMapping
	public Page<PatientDto> all(@RequestParam(name = "nom",required = false) String nom, @RequestParam(name="num-secu",required=false) String numSecu, @ParameterObject Pageable pageable){
		return patientService.getAll(nom, numSecu, pageable);
	}

}