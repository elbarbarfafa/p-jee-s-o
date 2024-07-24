package fr.elbarbary.p_jee_s_o.controllers;

/**
 * Cette interface présente les méthodes nécessaire à l'établissement des routes crud
 */
public interface ICrudRestController {

	void read();
	
	void add();
	
	void update();
	
	void delete();
	
}
