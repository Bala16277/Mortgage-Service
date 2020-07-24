package com.hcl.mortgage.exception;

public class UserNotFoundException extends Exception{
	
	/**
	 * @UserNotFoundException Exception to be thrown when user is not found
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message)  {
		super(message);
	}

}
