package fr.elbarbary.p_jee_s_o.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.elbarbary.p_jee_s_o.services.AuthService;

@RestController
public class AuthController {

	AuthService authservice;
	
	public AuthController(AuthService service) {
		this.authservice = service;
	}
	
	/**
	 * Cette route permet d'identifier un utilisateur et de servir un token
	 * @param authentication
	 * @return
	 */
	@PostMapping(path = "/token")
	public String authenticate(Authentication authentication)
	{
		String ret  = "";
		if(authentication != null)
		{
			ret = authservice.authenticate(authentication);
		}
		return ret;
	}
}