package cl.danceai.view;

import cl.danceai.audio.AudioCapture;
import cl.danceai.model.Move;
import cl.danceai.service.BeatDetector;
import cl.danceai.service.MoveGenerator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton btnMicrofono;
    private JButton btnDetener;

    private JLabel lblEstado;
    private JLabel lblNivel;
    private JLabel lblBeat;
    private JLabel lblMovimiento;
    private JLabel lblFlecha;

    private JProgressBar barraAudio;

    private AudioCapture audioCapture;
    private BeatDetector beatDetector;
    private MoveGenerator moveGenerator;

    private boolean microfonoActivo = false;

    public MainFrame() {

        audioCapture = new AudioCapture();
        beatDetector = new BeatDetector();
        moveGenerator = new MoveGenerator();

        setTitle("DanceAI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnMicrofono = new JButton("Iniciar Micrófono");
        btnDetener = new JButton("Detener");

        lblEstado = new JLabel("Estado: Desconectado");
        lblNivel = new JLabel("Nivel: 0%");
        lblBeat = new JLabel("Beat: NO");
        lblMovimiento = new JLabel("Movimiento: -");

        lblFlecha = new JLabel("-");
        lblFlecha.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        100
                )
        );

        barraAudio = new JProgressBar(0, 100);
        barraAudio.setValue(0);
        barraAudio.setStringPainted(true);

        add(btnMicrofono);
        add(btnDetener);

        add(lblEstado);
        add(lblNivel);
        add(lblBeat);
        add(lblMovimiento);

        add(lblFlecha);

        add(barraAudio);

        btnMicrofono.addActionListener(
                e -> iniciarMicrofono()
        );

        btnDetener.addActionListener(
                e -> detenerMicrofono()
        );
    }

    private void iniciarMicrofono() {

        if (microfonoActivo) {
            return;
        }

        microfonoActivo = true;

        lblEstado.setText(
                "Estado: Micrófono Activo"
        );

        new Thread(() -> {

            try {

                while (microfonoActivo) {

                    int nivel =
                            audioCapture.obtenerNivelAudio();

                    barraAudio.setValue(nivel);

                    lblNivel.setText(
                            "Nivel: " + nivel + "%"
                    );

                    if (beatDetector.detectarBeat(nivel)) {

                        lblBeat.setText(
                                "Beat: SI"
                        );

                        Move move =
                                moveGenerator.generarMovimiento();

                        lblMovimiento.setText(
                                "Movimiento: "
                                        + move.getDireccion()
                        );

                        lblFlecha.setText(
                                move.getDireccion()
                        );

                    } else {

                        lblBeat.setText(
                                "Beat: NO"
                        );

                    }

                    Thread.sleep(200);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }).start();

    }

    private void detenerMicrofono() {

        microfonoActivo = false;

        lblEstado.setText(
                "Estado: Desconectado"
        );

        lblNivel.setText(
                "Nivel: 0%"
        );

        lblBeat.setText(
                "Beat: NO"
        );

        lblMovimiento.setText(
                "Movimiento: -"
        );

        lblFlecha.setText(
                "-"
        );

        barraAudio.setValue(0);

    }

}