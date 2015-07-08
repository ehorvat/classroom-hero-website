package com.ego.exceptions;

public class RegPassMismatch extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Parameterless Constructor
    public RegPassMismatch() {}

    //Constructor that accepts a message
    public RegPassMismatch(String message)
    {
       super(message);
    }

}