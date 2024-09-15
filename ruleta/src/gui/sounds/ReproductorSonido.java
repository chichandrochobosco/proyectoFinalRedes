package gui.sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReproductorSonido {

    public void reproducirSonido(String nombreArchivo) {
        try {
            // Ruta del archivo de sonido
            File archivoSonido = new File("sounds/awp.wav");

            // Cargar el archivo de sonido
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);

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

