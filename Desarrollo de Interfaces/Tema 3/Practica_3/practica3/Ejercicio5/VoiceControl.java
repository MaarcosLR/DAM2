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
        // Configuración de Sphinx
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("file:libraries/resources/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("file:libraries/resources/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("file:libraries/resources/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        try {
            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
            recognizer.startRecognition(true);

            while (true) {
                String result = recognizer.getResult().getHypothesis();
                if ("activar".equalsIgnoreCase(result)) {
                    button.activateByVoice();  // Activa el botón
                    System.out.println("Comando de voz detectado: Activar");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
