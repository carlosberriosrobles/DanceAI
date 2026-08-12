package cl.danceai.view;

import cl.danceai.audio.AudioCapture;
import cl.danceai.model.Move;
import cl.danceai.model.Score;
import cl.danceai.service.BeatDetector;
import cl.danceai.service.MoveGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private JButton btnMicrofono;
    private JButton btnDetener;

    private JLabel lblEstado;
    private JLabel lblNivel;
    private JLabel lblBeat;
    private JLabel lblMovimiento;
    private JLabel lblFlecha;
    private JLabel lblResultado;

    private JLabel lblPuntaje;
    private JLabel lblCombo;
    private JLabel lblPrecision;
    private JLabel lblAciertos;
    private JLabel lblErrores;

    private JProgressBar barraAudio;

    private AudioCapture audioCapture;
    private BeatDetector beatDetector;
    private MoveGenerator moveGenerator;

    private Move movimientoActual;

    private Score score;

    private boolean microfonoActivo = false;

    public MainFrame() {

        audioCapture = new AudioCapture();
        beatDetector = new BeatDetector();
        moveGenerator = new MoveGenerator();

        score = new Score();

        setTitle("DanceAI");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        btnMicrofono = new JButton("Iniciar Micrófono");
        btnDetener = new JButton("Detener");

        lblEstado = new JLabel("Estado: Desconectado");
        lblNivel = new JLabel("Nivel: 0%");
        lblBeat = new JLabel("Beat: NO");

        lblMovimiento = new JLabel("Movimiento: -");

        lblResultado = new JLabel("Esperando movimiento...");

        lblPuntaje = new JLabel("Puntaje: 0");
        lblCombo = new JLabel("Combo: 0");
        lblPrecision = new JLabel("Precision: 0%");
        lblAciertos = new JLabel("Aciertos: 0");
        lblErrores = new JLabel("Errores: 0");

        lblFlecha = new JLabel("-");

        lblFlecha.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        120
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

        add(lblResultado);

        add(lblPuntaje);
        add(lblCombo);
        add(lblPrecision);
        add(lblAciertos);
        add(lblErrores);

        add(lblFlecha);

        add(barraAudio);

        btnMicrofono.addActionListener(
                e -> iniciarMicrofono()
        );

        btnDetener.addActionListener(
                e -> detenerMicrofono()
        );

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                validarMovimiento(e);

            }

        });

        setFocusable(true);

    }

    private void iniciarMicrofono() {

        requestFocusInWindow();

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

                        movimientoActual =
                                moveGenerator.generarMovimiento();

                        lblMovimiento.setText(
                                "Movimiento: "
                                        + movimientoActual.getDireccion()
                        );

                        lblFlecha.setText(
                                movimientoActual.getDireccion()
                        );

                    } else {

                        lblBeat.setText(
                                "Beat: NO"
                        );

                    }

                    Thread.sleep(300);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }).start();

    }

    private void validarMovimiento(
            KeyEvent e
    ) {

        if (movimientoActual == null) {
            return;
        }

        String tecla = "";

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                tecla = "←";
                break;

            case KeyEvent.VK_RIGHT:
                tecla = "→";
                break;

            case KeyEvent.VK_UP:
                tecla = "↑";
                break;

            case KeyEvent.VK_DOWN:
                tecla = "↓";
                break;

        }

        if (tecla.equals(
                movimientoActual.getDireccion()
        )) {

            score.registrarAcierto();

            lblResultado.setText(
                    "✅ CORRECTO"
            );

        } else {

            score.registrarError();

            lblResultado.setText(
                    "❌ INCORRECTO"
            );

        }

        actualizarEstadisticas();

    }

    private void actualizarEstadisticas() {

        lblPuntaje.setText(
                "Puntaje: "
                        + score.getPuntos()
        );

        lblCombo.setText(
                "Combo: "
                        + score.getCombo()
        );

        lblPrecision.setText(
                String.format(
                        "Precision: %.2f%%",
                        score.getPrecision()
                )
        );

        lblAciertos.setText(
                "Aciertos: "
                        + score.getAciertos()
        );

        lblErrores.setText(
                "Errores: "
                        + score.getErrores()
        );

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

        lblResultado.setText(
                "Esperando movimiento..."
        );

        lblFlecha.setText(
                "-"
        );

        barraAudio.setValue(0);

    }

}