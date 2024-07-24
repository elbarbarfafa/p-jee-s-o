package fr.elbarbary.p_jee_s_o.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ce contrôleur a pour but de définir des comportements par défaut pour les méthodes définies dans le contrat (interface)
 */
@RequestMapping(path = "/api")
@RestController
public abstract class BaseApiController implements ICrudRestController {
	
	
	
}
