package fr.elbarbary.p_jee_s_o.exceptions;

public class RestControllerControlledErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -315040912948030514L;

	public RestControllerControlledErrorException(String message) {
		super(message);
	}
	
}
