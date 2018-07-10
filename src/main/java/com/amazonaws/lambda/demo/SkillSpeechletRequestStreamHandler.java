package com.amazonaws.lambda.demo;

import java.util.HashSet;
import java.util.Set;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class SkillSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    
    static {
        String appId = "";  //add the ID of your Alexa skill here
        supportedApplicationIds.add(appId);
    }
 
    public SkillSpeechletRequestStreamHandler() {
        super(new SkillSpeechlet(), supportedApplicationIds);
    }
    
    public SkillSpeechletRequestStreamHandler(SpeechletV2 speechlet, Set<String> supportedApplicationIds) {
        super(speechlet, supportedApplicationIds);
    }
}