package ruleta;
import java.io.*;
import java.net.*;

public class ClienteChat {
    private static final String HOST = "192.168.56.1"; // Cambia esto por la IP del servidor
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            // Solicitar el nombre del usuario
            System.out.print("Introduce tu nombre: ");
            String nombreUsuario = stdIn.readLine();
            out.println(nombreUsuario);  // Enviar el nombre al servidor

            // Hilo para escuchar mensajes del servidor
            Thread lectorServidor = new Thread(() -> {
                String fromServer;
                try {
                    while ((fromServer = in.readLine()) != null) {
                        System.out.println(fromServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            lectorServidor.start();

            // Enviar mensajes al servidor
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

