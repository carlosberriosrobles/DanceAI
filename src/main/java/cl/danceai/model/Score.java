package cl.danceai.model;

public class Score {

    private int puntos;
    private int aciertos;
    private int errores;
    private int combo;
    private int comboMaximo;

    public Score() {

        this.puntos = 0;
        this.aciertos = 0;
        this.errores = 0;
        this.combo = 0;
        this.comboMaximo = 0;

    }

    public void registrarAcierto() {

        puntos += 100;

        aciertos++;

        combo++;

        if (combo > comboMaximo) {

            comboMaximo = combo;

        }

    }

    public void registrarError() {

        puntos -= 10;

        errores++;

        combo = 0;

    }

    public int getPuntos() {

        return puntos;

    }

    public int getAciertos() {

        return aciertos;

    }

    public int getErrores() {

        return errores;

    }

    public int getCombo() {

        return combo;

    }

    public int getComboMaximo() {

        return comboMaximo;

    }

    public double getPrecision() {

        int total = aciertos + errores;

        if (total == 0) {

            return 0;

        }

        return (aciertos * 100.0) / total;

    }

}