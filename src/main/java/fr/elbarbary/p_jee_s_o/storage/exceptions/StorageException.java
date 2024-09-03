package fr.elbarbary.p_jee_s_o.storage.exceptions;

public class StorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -635364035922382242L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
