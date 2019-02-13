package org.anup.programmingSearch.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * Exception class
	 */
	private static final long serialVersionUID = -2185469831840074754L;

	public DataNotFoundException(String message){
		super(message);
	}
}
