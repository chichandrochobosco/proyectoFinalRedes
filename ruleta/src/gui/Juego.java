package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Juego extends JFrame {

    private JPanel panelJuego;
    private JButton btnDisparar;
    private JButton btnSalir;
    private JLabel lblVidas1;
    private JLabel lblVidas2;
    private JLabel lblTurno;
    //private JLabel msj;
    private int vidasJugador1 = 3;
    private int vidasJugador2 = 3;
    private int balaPosicion;
    private boolean turnoJugador1 = true; // Control del turno

    public Juego() {
        setTitle("Ruleta Rusa - Juego");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel del juego
        panelJuego = new JPanel();
        panelJuego.setLayout(new GridLayout(5, 1));

        // Etiquetas de vida y turno
        lblVidas1 = new JLabel("Vidas Jugador 1: " + vidasJugador1);
        lblVidas2 = new JLabel("Vidas Jugador 2: " + vidasJugador2);
        lblTurno = new JLabel("Turno de Jugador 1");
        //msj = new JLabel("");
        
        // Botones
        btnDisparar = new JButton("Disparar");
        btnSalir = new JButton("Salir al Menú");

        // Agregamos los componentes al panel
        panelJuego.add(lblVidas1);
        panelJuego.add(lblVidas2);
        panelJuego.add(lblTurno);
        //panelJuego.add(msj);
        panelJuego.add(btnDisparar);
        panelJuego.add(btnSalir);
        

        // Agregar el panel al JFrame
        add(panelJuego);

        // Generar la posición aleatoria de la bala
        Random random = new Random();
        balaPosicion = random.nextInt(8) + 1;

        // Evento para disparar
        btnDisparar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disparar(); // Ejecutar lógica de disparo
            }
        });

        // Evento para salir al menú
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RuletaRusa menu = new RuletaRusa(); // Volver al menú principal
                menu.setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });
    }

    private void disparar() {
        //msj.setText("");
        Random random = new Random();
        int disparo = random.nextInt(8) + 1; // Generar número aleatorio entre 1 y 8
        if(turnoJugador1){
            System.out.println("jugador 1, Disparo en posición: " + disparo + " (Bala en posición: " + balaPosicion + ")");
        }else{
            System.out.println("jugador 2, Disparo en posición: " + disparo + " (Bala en posición: " + balaPosicion + ")");
        }
        

        // Verificar si el disparo coincide con la posición de la bala
        if (disparo == balaPosicion) {
            if (turnoJugador1) {
                vidasJugador1--;
                lblVidas1.setText("Vidas Jugador 1: " + vidasJugador1);
                
            } else {
                vidasJugador2--;
                lblVidas2.setText("Vidas Jugador 2: " + vidasJugador2);
                
            }
            // Reposicionar la bala aleatoriamente para el siguiente turno
            JOptionPane.showMessageDialog(this, "DISPARO EN POSICION "+balaPosicion+", recargando tambor...");
            //msj.setText("Recargando tambor");
            balaPosicion = random.nextInt(8) + 1;
        }
        

        // Cambiar turno
        turnoJugador1 = !turnoJugador1;
        lblTurno.setText(turnoJugador1 ? "Turno de Jugador 1" : "Turno de Jugador 2");

        // Verificar si un jugador ha perdido todas sus vidas
        verificarFinJuego();
    }

    private void verificarFinJuego() {
        if (vidasJugador1 == 0) {
            JOptionPane.showMessageDialog(this, "¡Jugador 2 gana!");
            regresarAlMenu();
        } else if (vidasJugador2 == 0) {
            JOptionPane.showMessageDialog(this, "¡Jugador 1 gana!");
            regresarAlMenu();
        }
    }

    private void regresarAlMenu() {
        RuletaRusa menu = new RuletaRusa(); // Volver al menú principal
        menu.setVisible(true);
        dispose(); // Cerrar la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true); // Mostrar la ventana del juego
            }
        });
    }
}
