package com.amazonaws.lambda.demo;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillSpeechlet implements SpeechletV2 {
	
	private Responder responder;
		
	private static final Logger log = LoggerFactory.getLogger(SkillSpeechlet.class);

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        log.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());	    	
        // any code for setting up variables, data structures, etc can go here
        responder = new Responder();
    }	    
    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        log.info("onSessionEnd requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());
        // any cleanup logic goes here
    }

    
    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        log.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());	
        //welcomeResponse() will be programmed to deliver a welcome message
        return speakMessage(responder.getWelcomeMessage(), false);
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {

       //get the intent - the Alexa interface will match an utterance with an intent
        Intent intent = requestEnvelope.getRequest().getIntent();
        String intentName = (intent != null) ? intent.getName() : null;  //intentName is now the name of the intent that matches the utterance

        // deal with all the possible intents
        return speakMessage(responder.getIntentMessage(intentName), responder.getResponseStatus(intentName));
    }
    
    // speak message will say the given message and then if INTERIM it will cause Alexa to wait for another intent.  FINAL means that this is the end.
    private SpeechletResponse speakMessage(String message, boolean finalMessage) {
    	SpeechletResponse response;
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(message);
	        
        if(finalMessage) {
	        response = SpeechletResponse.newTellResponse(speech); 
        }
        else {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText("Can you please repeat it?");
            
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);
            
            response = SpeechletResponse.newAskResponse(speech, reprompt);    	
        }
        
        return response;
    }
}
