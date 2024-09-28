package gui.sounds;
/*
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReproductorSonido {

    public void reproducirSonido(String nombreArchivo) {
        try {
            // Ruta del archivo de sonido
            File archivoSonido = new File("sounds/awp.wav");
            File archivoSonido2 = new File("sounds/recarga.wav");
            File archivoSonido3 = new File("sounds/girotambor.wav");

            // Cargar el archivo de sonido
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(archivoSonido2);
            AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(archivoSonido3);

            // Crear el clip de sonido
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Reproducir el sonido
            clip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
*/
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class ReproductorSonido {

    // Método para reproducir cualquier archivo de sonido
    public static void reproducirSonido(String rutaSonido) {
        try {
            // Cargar el archivo de sonido desde la ruta pasada como argumento
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(rutaSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            
            // Reproducir el sonido
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir sonido: " + e.getMessage());
        }
    }
}


