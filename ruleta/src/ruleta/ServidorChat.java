
package ruleta;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    private static final int PUERTO = 12345;
    private static Set<ClienteHandler> clientes = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                new ClienteHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String nombreUsuario;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Leer el nombre del cliente
                nombreUsuario = in.readLine();
                System.out.println(nombreUsuario + " se ha unido al chat.");

                synchronized (clientes) {
                    clientes.add(this);
                }

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println(nombreUsuario + ": " + input);
                    synchronized (clientes) {
                        for (ClienteHandler cliente : clientes) {
                            cliente.out.println(nombreUsuario + ": " + input);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientes) {
                    clientes.remove(this);
                }
                System.out.println(nombreUsuario + " ha salido del chat.");
            }
        }
    }
}

