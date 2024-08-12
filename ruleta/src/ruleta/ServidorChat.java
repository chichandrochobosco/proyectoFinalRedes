
package ruleta;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    private static final int PUERTO = 12345;
    private static Set<PrintWriter> clientes = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                new ManejadorClientes(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorClientes extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ManejadorClientes(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientes) {
                    clientes.add(out);
                }

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + input);
                    synchronized (clientes) {
                        for (PrintWriter cliente : clientes) {
                            cliente.println(input);
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
                    clientes.remove(out);
                }
            }
        }
    }
}
