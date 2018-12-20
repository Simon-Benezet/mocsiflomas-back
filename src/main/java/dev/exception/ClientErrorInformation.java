package dev.exception;
public class ClientErrorInformation {

	private String message;
	private String requestURI;
	

	/**
	 * Constructor
	 */
	public ClientErrorInformation() {
		
	}
	
	/** Constructor
	 * @param message
	 * @param requestURI
	 */
	public ClientErrorInformation(String message, String requestURI) {
		super();
		this.message = message;
		this.requestURI = requestURI;
	}


	/** Getter for message
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/** Setter
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/** Getter for requestURI
	 * @return the requestURI
	 */
	public String getRequestURI() {
		return requestURI;
	}

	/** Setter
	 * @param requestURI the requestURI to set
	 */
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

}