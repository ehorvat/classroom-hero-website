package com.ego.exceptions;

public class TokenExpired extends Exception{
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		//Parameterless Constructor
	    public TokenExpired() {}

	    //Constructor that accepts a message
	    public TokenExpired(String message)
	    {
	       super(message);
	    }
}
