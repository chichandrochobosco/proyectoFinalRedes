package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfirmacionJugadores extends JFrame {

    private JPanel panelConfirmacion;
    private JButton btnJugador1Listo;
    private JButton btnJugador2Listo;
    private boolean jugador1Listo = false;
    private boolean jugador2Listo = false;

    public ConfirmacionJugadores() {
        setTitle("Confirmación de Jugadores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel para confirmar a los jugadores
        panelConfirmacion = new JPanel();
        panelConfirmacion.setLayout(new GridLayout(2, 1));

        btnJugador1Listo = new JButton("Jugador 1 Listo");
        btnJugador2Listo = new JButton("Jugador 2 Listo");

        panelConfirmacion.add(btnJugador1Listo);
        panelConfirmacion.add(btnJugador2Listo);

        add(panelConfirmacion);

        // Eventos para confirmar jugadores
        btnJugador1Listo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugador1Listo = true;
                verificarListos();
            }
        });

        btnJugador2Listo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugador2Listo = true;
                verificarListos();
            }
        });
        
    }

    public void verificarListos() {
        if (jugador1Listo && jugador2Listo) {
            // Si ambos jugadores están listos, iniciamos la partida
            Juego juego = new Juego();
            juego.setVisible(true);
            this.dispose();
        }
    }
}

