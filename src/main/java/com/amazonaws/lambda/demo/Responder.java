package com.amazonaws.lambda.demo;


public class Responder {
	
	// CLASS CONSTANT VALUES-------------------------------------------------------------//
	private final boolean FINAL = true;
	private final boolean INTERIM = false;
	
	// CLASS VARIABLES-------------------------------------------------------------------//

	
	// CONSTRUCTOR-----------------------------------------------------------------------//
	public Responder() {
		
	}
	
	
	// GET WELCOME MESSAGE---------------------------------------------------------------//
	public String getWelcomeMessage() {
    	return "";
	}
	
	
	// GET INTENT MESSAGE----------------------------------------------------------------//
	public String getIntentMessage(String intentName) {
		String responseMessage = "";

		return responseMessage;
	}
	
	
	// GET RESPONSE STATUS---------------------------------------------------------------//
	public boolean getResponseStatus(String intentName) {
		boolean status = INTERIM;

		return status;
	}

	
	// END OF RESPONDER CLASS------------------------------------------------------------//
}
