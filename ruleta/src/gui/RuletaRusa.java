package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RuletaRusa extends JFrame {

    private JPanel panelMenu;
    private JButton btnIniciar;
    private JButton btnSalir;

    public RuletaRusa() {
        setTitle("Ruleta Rusa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de menú
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(2, 1));

        btnIniciar = new JButton("Iniciar Partida");
        btnSalir = new JButton("Salir");

        panelMenu.add(btnIniciar);
        panelMenu.add(btnSalir);

        add(panelMenu);

        // Eventos
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void iniciarJuego() {
        // Abrimos una nueva ventana para la confirmación de jugadores
        ConfirmacionJugadores confirmacion = new ConfirmacionJugadores();
        confirmacion.setVisible(true);
        this.dispose(); // Cerramos la ventana del menú
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RuletaRusa().setVisible(true);
            }
        });
    }
}

