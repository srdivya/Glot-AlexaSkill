import java.util.HashSet;
import java.util.Set;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.OutputSpeech;
import java.io.*;

public class GlotSpeechlet implements SpeechletV2
{

    private static final Logger log = LoggerFactory.getLogger(GlotSpeechlet.class);
    private static int nOption;
    private static int nQuestion = 0;
    private static String[][] strResponses = {{" I'm Azul because of the cold!", "I'm so angry! I see rojo everywhere.", "I wish I had that dress! I'm verde with envy" , " Oh no do you have jaundice? You look Amarillo!" , " I'm in a very Negro mood right now" , " Silver lining in the gris cloud" , " Unlike white chocolate, dark chocolate is marron" , " Barney the Púrpura dinosaur!" , " I'm in the rosado of my health!" , " Go gators! We bleed blue and Naranja!" , " Remember when she saw a ghost? Her face was blanco as a sheet!"},
    		{"sky, sea, sapphire" , " rose, blood, strawberry, cherry, ruby" , " leaf, grass, caterpillar, grasshopper" , " sun, lemon, bananas" , " coal, blackberries, raven, crow" , " elephant, koala" , " chocolate, coffee, soil, brownies" , " eggplant, grapes, lavender" , " bubblegum, ham, flamingo" , " tangerine, carrot, pumpkin, goldfish" , " snow, marshmallow, polar bear, cotton, milk"}};
	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope)
	{
		log.info("onSessionStarted requestId={}, sessionId={}",
                requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope)
	{
		log.info("onLaunch requestId={}, sessionId={}",
                requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
        return getWelcomeResponse();
	}

	private SpeechletResponse getWelcomeResponse()
	{
		String speechText = "Welcome to Glot! Your new language buddy. Say start to proceed.";
        return getAskResponse("HelloWorld", speechText);
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		IntentRequest request = requestEnvelope.getRequest();
        Intent intent = request.getIntent();
        log.info("onIntent requestId={}, sessionId={}, intent = {}", request.getRequestId(),
                requestEnvelope.getSession().getSessionId(), intent.getName());
        String intentName = (intent != null) ? intent.getName() : null;
        int val = nOption == 0? 0 : nOption - 1;
        int val2 = nQuestion == strResponses[0].length ? nQuestion - 1 : nQuestion;
        String nextResponse = strResponses[val][val2];

        switch (intentName)
        {
			case "StartIntent":
				return getStartResponse("");
			case "FirstOptionIntent":
				nOption = 1;
				String[] response = {"Oh, you want to go with the first option? ", "You want me to give you sentences? That's great! "};
				nQuestion = 1;
				nextResponse = strResponses[0][0];
				if(Math.random() < 0.5)
					return getAskResponse("Glot", response[0] + nextResponse);
				else
					return getAskResponse("Glot", response[1] + nextResponse);
			case "SecondOptionIntent":
				nOption = 2;
				nQuestion = 1;
//				nextResponse = "sky, sea, sapphire";
				String[] response2 = {"What was that? You want me to list objects? Let's get started. ", "You'd like me to list things in that colour? Okay! "};
				nextResponse = strResponses[1][0];
				if(Math.random() < 0.5)
					return getAskResponse("Glot", response2[0] + nextResponse);
				else
					return getAskResponse("Glot", response2[1] + nextResponse);
			case "BlueIntent":
				if (nQuestion == 1)
				{
					nQuestion = 2;
					nextResponse = "That's right! Your next statement is as follows. " + nextResponse;
//					nextResponse += nOption == 1 ? "I'm so angry! I see rojo everywhere."
//							: "rose, blood, strawberry, cherry, ruby";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "RedIntent":
				if (nQuestion == 2)
				{
					nQuestion = 3;
					nextResponse = "Right answer! Let's move on. " + nextResponse;
//					nextResponse += nOption == 1 ? "I wish I had that dress! I'm verde with envy."
//							: "leaf, grass, caterpillar, grasshopper";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "GreenIntent":
				if (nQuestion == 3)
				{
					nQuestion = 4;
					nextResponse = "Good job! Next, " + nextResponse;
//					nextResponse += nOption == 1 ? "Oh no! Do you have jaundice? You look Amarillo!"
//							: "sun, lemon, bananas";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "YellowIntent":
				if (nQuestion == 4)
				{
					nQuestion = 5;
					nextResponse = "Perfect! Next statement is as follows. " + nextResponse;
//					nextResponse += nOption == 1 ? "I'm in a very Negro mood right now."
//							: "coal, blackberries, raven, crow";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "BlackIntent":
				if (nQuestion == 5)
				{
					nQuestion = 6;
					nextResponse = "Great! Moving onto the next one." + nextResponse;
//					nextResponse += nOption == 1 ? "Silver lining in the gris cloud." : "elephant, koala";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "GrayIntent":
				if (nQuestion == 6)
				{
					nQuestion = 7;
					nextResponse = "You're getting the hang of this! Let's try a different color next. " + nextResponse;
//					nextResponse += nOption == 1 ? "Unlike white chocolate, dark chocolate is marron"
//							: "chocolate, coffee, soil, brownies";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "BrownIntent":
				if (nQuestion == 7)
				{
					nQuestion = 8;
					nextResponse = "Give me a high five! Let's go to the next color. " + nextResponse;
//					nextResponse += nOption == 1 ? "	" : "eggplant, grapes, lavender";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "PurpleIntent":
				if (nQuestion == 8)
				{
					nQuestion = 9;
					nextResponse = "Nailed it! Next colour. " + nextResponse;
//					nextResponse += nOption == 1 ? "I'm feeling good! I'm in the rosado of my health!"
//							: "bubblegum, ham, flamingo";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "PinkIntent":
				if (nQuestion == 9)
				{
					nQuestion = 10;
					nextResponse = "Wow you're on a roll! Let's see if you can keep this up! " + nextResponse;
//					nextResponse += nOption == 1 ? "Go gators! We bleed blue and Naranja!"
//							: "tangerine, carrot, pumpkin, goldfish";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "OrangeIntent":
				if (nQuestion == 10)
				{
					nQuestion = 11;
					nextResponse = "You're a pro! One more to go. " + nextResponse;
//					nextResponse += nOption == 1 ? "Remember when she saw a ghost? Her face was blanco as a sheet!"
//							: "snow, marshmallow, polar bear, cotton, milk";
					return getAskResponse("Glot", nextResponse);
				}
				else
				{
					nOption = 0;
					nQuestion = 0;
					log.info("restarting");
					return getStartResponse("Oh no! Let's try again. ");
				}
			case "WhiteIntent":
				if (nQuestion == 11)
				{
					nQuestion = 0;
					nextResponse = "That's all folks! Hope you enjoyed this. Say restart to restart or stop to exit.";
					return getAskResponse("Glot", nextResponse);
				}
			case "RestartIntent":
				if (nQuestion == 0)
				{
					nOption = 0;
					return getStartResponse("Glot is restarting. ");
				}
//				else if(nQuestion != 0 && nOption != 0)
//				{
//					nOption = 0;
////					log.info("restartIntent text={}");
//					return getStartResponse("Oh no! Let's try again. ");
//
//				}
				else
				{
					nQuestion = 0;
					nOption = 0;
					return getStartResponse("");
				}
			case "AMAZON.HelpIntent":
				nextResponse = "You can say Cancel or Stop to exit Glot. You can also say Say restart to restart Glot. Or restate previous answer to proceed.";
				return getAskResponse("Glot", nextResponse);
			case "AMAZON.RepeatIntent":
				return getAskResponse("Glot", nextResponse);
			case "AMAZON.StopIntent":
			case "AMAZON.CancelIntent":
				SpeechletResponse speechResponse = new SpeechletResponse();
				speechResponse.setShouldEndSession(true);
				String speechText = "Thank you for using Glot. Have a nice day!";
				SimpleCard card = getSimpleCard("Glot", speechText);
				speechResponse.setCard(card);
				return speechResponse;
			default:
				nextResponse = "I'm sorry I did not understand that. Please repeat.";
				return getAskResponse("Glot", nextResponse);
		}
	}

	private SpeechletResponse handleRepeat(SpeechletResponse response)
	{
		//SpeechletResponse response = new SpeechletResponse();
		response.setShouldEndSession(false);
		return response;
	}

	private SpeechletResponse getStartResponse(String text)
	{
		String speechText = text + " This is your first lesson."
				+ " Let me teach you colours in Spanish."
				+ " Blue is Azul."
				+ " Red is Rojo. "
				+ "Green is Verde. "
				+ "Yellow is Amarillo. "
				+ "Black is Negro. "
				+ "Gray is Gris. "
				+ "Brown is Marron. "
				+ "Purple is Púrpura. "
				+ "Pink is Rosado. "
				+ "Orange is Naranja. "
				+ "White is Blanco. "
				+ "You now have two choices. You can either tell me what colour I'm talking about when I use it in a sentence. "
				+ "Or your other option is to guess the colour in Spanish based on the objects or things that I list."
				+ " You will have one try to get everything right. Glot restarts if you get even one question wrong! Good luck!"
				+ "So what do you want to do now?";
		// Create the Simple card content.
		log.info("startresp = {}", speechText);
        SimpleCard card = getSimpleCard("Glot", speechText);
        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
	}

	private SimpleCard getSimpleCard(String title, String content)
	{
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);
        return card;
    }

	private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText)
	{
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }


	private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);

        return reprompt;
    }

	private SpeechletResponse getAskResponse(String cardTitle, String speechText)
	{
		log.info("getAskResponse text={}", speechText);
		// Create the Simple card content.
        SimpleCard card = getSimpleCard("Glot", speechText);
        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope)
	{
		 log.info("onSessionEnd requestId={}, sessionId={}",
	                requestEnvelope.getRequest().getRequestId(),
	                requestEnvelope.getSession().getSessionId());

	}

}
