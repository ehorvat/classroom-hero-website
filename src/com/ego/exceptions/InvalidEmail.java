package com.ego.exceptions;

public class InvalidEmail extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Parameterless Constructor
   public InvalidEmail() {}

   //Constructor that accepts a message
   public InvalidEmail(String message)
   {
      super(message);
   }

}