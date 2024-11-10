package practica3.Ejercicio5;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import java.io.IOException;

public class VoiceControl {
    private SmartButton button;

    public VoiceControl(SmartButton button) {
        this.button = button;
    }

    public void startVoiceRecognition() {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        try {
            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
            recognizer.startRecognition(true);

            while (true) {
                String result = recognizer.getResult().getHypothesis();
                if (result.equalsIgnoreCase("activar")) {
                    button.triggerClick();  // Activa el botón
                    System.out.println("Comando de voz: Activar");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
