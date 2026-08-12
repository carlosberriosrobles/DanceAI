package cl.danceai.view;

import javax.swing.*;
import java.awt.*;

public class AvatarPanel extends JPanel {

    private String movimiento = "-";

    public void setMovimiento(String movimiento) {

        this.movimiento = movimiento;

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        // Fondo

        g2.setColor(new Color(240, 240, 240));

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // Piso

        g2.setColor(new Color(180, 180, 180));

        g2.fillRect(
                0,
                getHeight() - 60,
                getWidth(),
                60
        );

        // Título

        g2.setColor(Color.BLACK);

        g2.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        g2.drawString(
                "DanceAI",
                centroX - 60,
                50
        );

        int cabezaX = centroX;
        int cabezaY = centroY - 150;

        // Cabeza

        g2.setColor(
                new Color(
                        255,
                        220,
                        180
                )
        );

        g2.fillOval(
                cabezaX - 35,
                cabezaY,
                70,
                70
        );

        g2.setColor(Color.BLACK);

        g2.drawOval(
                cabezaX - 35,
                cabezaY,
                70,
                70
        );

        // Ojos

        g2.fillOval(
                cabezaX - 15,
                cabezaY + 22,
                6,
                6
        );

        g2.fillOval(
                cabezaX + 9,
                cabezaY + 22,
                6,
                6
        );

        // Sonrisa

        g2.drawArc(
                cabezaX - 15,
                cabezaY + 30,
                30,
                15,
                180,
                180
        );

        // Cuello

        g2.drawLine(
                cabezaX,
                cabezaY + 70,
                cabezaX,
                cabezaY + 85
        );

        // Torso

        g2.setColor(
                new Color(
                        50,
                        120,
                        255
                )
        );

        g2.fillRoundRect(
                cabezaX - 30,
                cabezaY + 85,
                60,
                90,
                20,
                20
        );

        g2.setColor(Color.BLACK);

        g2.drawRoundRect(
                cabezaX - 30,
                cabezaY + 85,
                60,
                90,
                20,
                20
        );

        g2.setStroke(
                new BasicStroke(5)
        );

        // Hombros

        int hombroIzqX = cabezaX - 25;
        int hombroDerX = cabezaX + 25;
        int hombroY = cabezaY + 105;

        // Brazos

        if (movimiento.equals("↑")) {

            g2.drawLine(
                    hombroIzqX,
                    hombroY,
                    cabezaX - 70,
                    cabezaY + 20
            );

            g2.drawLine(
                    hombroDerX,
                    hombroY,
                    cabezaX + 70,
                    cabezaY + 20
            );

        } else if (movimiento.equals("←")) {

            g2.drawLine(
                    hombroIzqX,
                    hombroY,
                    cabezaX - 100,
                    hombroY
            );

            g2.drawLine(
                    hombroDerX,
                    hombroY,
                    cabezaX + 40,
                    hombroY + 20
            );

        } else if (movimiento.equals("→")) {

            g2.drawLine(
                    hombroIzqX,
                    hombroY,
                    cabezaX - 40,
                    hombroY + 20
            );

            g2.drawLine(
                    hombroDerX,
                    hombroY,
                    cabezaX + 100,
                    hombroY
            );

        } else {

            g2.drawLine(
                    hombroIzqX,
                    hombroY,
                    cabezaX - 60,
                    hombroY + 40
            );

            g2.drawLine(
                    hombroDerX,
                    hombroY,
                    cabezaX + 60,
                    hombroY + 40
            );

        }

        // Manos

        g2.fillOval(
                cabezaX - 105,
                hombroY - 5,
                10,
                10
        );

        g2.fillOval(
                cabezaX + 95,
                hombroY - 5,
                10,
                10
        );

        // Cadera

        int caderaY = cabezaY + 175;

        // Pierna izquierda

        g2.drawLine(
                cabezaX - 10,
                caderaY,
                cabezaX - 35,
                caderaY + 65
        );

        g2.drawLine(
                cabezaX - 35,
                caderaY + 65,
                cabezaX - 45,
                caderaY + 120
        );

        // Pierna derecha

        g2.drawLine(
                cabezaX + 10,
                caderaY,
                cabezaX + 35,
                caderaY + 65
        );

        g2.drawLine(
                cabezaX + 35,
                caderaY + 65,
                cabezaX + 45,
                caderaY + 120
        );

        // Pies

        g2.drawLine(
                cabezaX - 60,
                caderaY + 120,
                cabezaX - 30,
                caderaY + 120
        );

        g2.drawLine(
                cabezaX + 30,
                caderaY + 120,
                cabezaX + 60,
                caderaY + 120
        );

    }

}