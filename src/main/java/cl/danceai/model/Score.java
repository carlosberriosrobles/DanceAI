package cl.danceai.model;

public class Score {

    private int puntos;

    public Score() {

        this.puntos = 0;

    }

    public void sumar(int valor) {

        puntos += valor;

    }

    public void restar(int valor) {

        puntos -= valor;

    }

    public int getPuntos() {

        return puntos;

    }

}