package cl.danceai.service;

import cl.danceai.model.Move;

public class MoveGenerator {

    public Move generarMovimiento() {

        int numero = (int)(Math.random() * 4);

        switch (numero) {

            case 0:
                return new Move("←");

            case 1:
                return new Move("→");

            case 2:
                return new Move("↑");

            default:
                return new Move("↓");

        }

    }

}