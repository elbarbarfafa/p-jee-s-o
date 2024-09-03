package fr.elbarbary.p_jee_s_o.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.dtos.PrescriptionDetailDto;
import fr.elbarbary.p_jee_s_o.exceptions.DateIsBeforeException;
import fr.elbarbary.p_jee_s_o.exceptions.DateIsNullException;
import fr.elbarbary.p_jee_s_o.exceptions.NotFoundException;
import fr.elbarbary.p_jee_s_o.exceptions.RestControllerControlledErrorException;
import fr.elbarbary.p_jee_s_o.services.ConsultationService;
import fr.elbarbary.p_jee_s_o.services.ConsultationServiceImpl;
import fr.elbarbary.p_jee_s_o.services.PrescriptionService;
import fr.elbarbary.p_jee_s_o.storage.StorageService;

@RestController
@RequestMapping(path = "/api/consultations")
public class ConsultationController {

	private final ConsultationService consultationService;
	private final PrescriptionService prescriptionService;

	Logger logger = LoggerFactory.getLogger(ConsultationController.class);

	public ConsultationController(ConsultationServiceImpl service, PrescriptionService prescriptionService) {
		this.consultationService = service;
		this.prescriptionService = prescriptionService;
	}

	@PostMapping
	public ResponseEntity<Object> add(@RequestBody ConsultationDto dto) {
		try {
			ConsultationDto ret = this.consultationService.add(dto);
			return new ResponseEntity<>(ret, HttpStatus.OK);
		} catch (DateIsNullException | DateIsBeforeException e) {
			logger.info(
					"Echec de l'ajout d'une consultation: la date n'est pas renseignée ou n'est pas postérieure à aujourd'hui.");
			throw new RestControllerControlledErrorException(
					"Echec de l'ajout d'une consultation: la date n'est pas renseignée ou n'est pas postérieure à aujourd'hui.");
		} catch (Exception e) {
			String temp = "";
			for (StackTraceElement straceElement : e.getStackTrace()) {
				temp = String.join("\r\n", temp, straceElement.toString());
			}
			logger.error(temp);
			throw new RestControllerControlledErrorException(
					"Echec de l'ajout d'une consultation: une erreur inconnue s'est produite.");
		}

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<ConsultationDto> edit(@PathVariable int id, @RequestBody ConsultationDto dto) {
		try {
			ConsultationDto ret = this.consultationService.edit(id, dto);
			return new ResponseEntity<>(ret, HttpStatus.OK);
		} catch (DateIsNullException | DateIsBeforeException e) {
			logger.info(String.format(
					"Echec de la modification d'une consultation: la date n'est pas renseignée ou n'est pas postérieure à aujourd'hui. (%d)",
					id));
			throw new RestControllerControlledErrorException(String.format(
					"Echec de la modification d'une consultation: la date n'est pas renseignée ou n'est pas postérieure à aujourd'hui. (%d)",
					id));
		} catch (NotFoundException e) {
			logger.info(String.format(
					"Echec de la modification d'une consultation: aucune consultation ne possède cet identifiant ({%d})",
					id));
			throw new RestControllerControlledErrorException(String.format(
					"Echec de la modification d'une consultation: aucune consultation ne possède cet identifiant ({%d})",
					id));
		}
	}

	/**
	 * Détailler les médicaments prescrits dans une consultation
	 * 
	 * @param id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/{id}/medicaments")
	public Page<PrescriptionDetailDto> getMedicaments(@PathVariable(name = "id") int id, @ParameterObject Pageable pageable) {
		return prescriptionService.findMedicamentsByConsultationNumero(id, pageable);
	}

	/**
	 * Cette route permet de remplacer l'ensemble des prescriptions
	 * @param id : de la consultation
	 * @param prescriptionsDto : Liste des prescriptions à attribuer à cette consultation
	 * @return La liste des prescriptions attribuées
	 */
	@PutMapping(path = "/{id}/prescriptions")
	public List<PrescriptionDetailDto> updatePrescriptions(@PathVariable(name = "id") int id, @RequestBody List<PrescriptionDetailDto> prescriptionsDto) {
		return prescriptionService.replacePrescriptionsByConsultationNumero(id, prescriptionsDto);
	}

	@PostMapping("/{id}/document")
	public ConsultationDto uploadDocument(@PathVariable(name = "id") int id, @RequestParam(name = "file") MultipartFile file) {
		return consultationService.setDocument(id, file);
	}

}
