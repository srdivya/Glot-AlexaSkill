import java.util.HashSet;
import java.util.Set;

//import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
public class GlotSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    static {
        String appId = "amzn1.ask.skill.83f466c0-cd93-477e-b89d-3c683a638b6f";//System.getenv("APP_ID");
        supportedApplicationIds.add(appId);
    }

    public GlotSpeechletRequestStreamHandler() {
        super(new GlotSpeechlet(), supportedApplicationIds);
    }
}