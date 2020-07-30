package test.response;

public class MessageResponse {

	private String message;

	/**
	 * @param message
	 */
	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * 
	 */
	public MessageResponse() {
		super();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
