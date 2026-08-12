package cl.danceai.service;

import cl.danceai.model.Song;

public class SongManager {

    private Song song;

    private int tiempoActual;

    public SongManager() {

        song = new Song(
                "Demo DanceAI",
                60
        );

        tiempoActual = 0;

    }

    public Song getSong() {

        return song;

    }

    public int getTiempoActual() {

        return tiempoActual;

    }

    public void avanzarSegundo() {

        tiempoActual++;

    }

    public boolean finalizada() {

        return tiempoActual >=
                song.getDuracionSegundos();

    }

    public String getTiempoFormateado() {

        int restantes =
                song.getDuracionSegundos()
                        - tiempoActual;

        if (restantes < 0) {

            restantes = 0;

        }

        int minutos = restantes / 60;

        int segundos = restantes % 60;

        return String.format(
                "%02d:%02d",
                minutos,
                segundos
        );

    }

}