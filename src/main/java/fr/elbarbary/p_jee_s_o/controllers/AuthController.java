package fr.elbarbary.p_jee_s_o.controllers;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@PostMapping(path = "authenticate")
	public void authenticate(AuthenticatedPrincipal login)
	{
		
	}
}