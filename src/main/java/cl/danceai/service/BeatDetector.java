package cl.danceai.service;

public class BeatDetector {

    public boolean detectarBeat(int nivelAudio) {

        return nivelAudio > 80;

    }

}