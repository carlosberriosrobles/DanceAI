package cl.danceai.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton btnMicrofono;
    private JLabel lblEstado;
    private JProgressBar barraAudio;

    public MainFrame() {

        setTitle("DanceAI");

        setSize(800, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        btnMicrofono = new JButton("Iniciar Micrófono");

        lblEstado = new JLabel("Estado: Desconectado");

        barraAudio = new JProgressBar(0, 100);

        barraAudio.setValue(0);

        barraAudio.setStringPainted(true);

        add(btnMicrofono);
        add(lblEstado);
        add(barraAudio);

        btnMicrofono.addActionListener(
                e -> iniciarMicrofono()
        );
    }

    private void iniciarMicrofono() {

        lblEstado.setText(
                "Estado: Micrófono Activo"
        );

        new Thread(() -> {

            try {

                while (true) {

                    int nivel =
                            (int) (Math.random() * 100);

                    barraAudio.setValue(nivel);

                    Thread.sleep(200);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }).start();

    }

}