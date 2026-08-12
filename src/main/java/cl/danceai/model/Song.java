package cl.danceai.model;

public class Song {

    private String nombre;

    private int duracionSegundos;

    public Song(
            String nombre,
            int duracionSegundos
    ) {

        this.nombre = nombre;
        this.duracionSegundos =
                duracionSegundos;

    }

    public String getNombre() {

        return nombre;

    }

    public int getDuracionSegundos() {

        return duracionSegundos;

    }

}