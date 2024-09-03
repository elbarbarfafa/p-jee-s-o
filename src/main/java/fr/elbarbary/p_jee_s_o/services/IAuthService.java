package fr.elbarbary.p_jee_s_o.services;

import org.springframework.security.core.Authentication;

public interface IAuthService {

	/**
	 * Crée un token JWT basé sur l'authentication avec sa durée et les rôles
	 * @param auth 
	 * @return le token JWT en format String.
	 */
	public String authenticate(Authentication auth);
	
}
