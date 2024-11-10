package practica3.Ejercicio5;

import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CameraTest {

    @Test
    public void testCameraInitialization() {
        // Cargar la librería de OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Crear una captura de video (debe estar configurada correctamente en el sistema)
        VideoCapture capture = new VideoCapture(0);

        // Comprobar si la cámara se ha abierto correctamente
        assertTrue(capture.isOpened(), "La cámara no se pudo abrir.");

        // Liberar los recursos de la cámara
        capture.release();
    }
}
