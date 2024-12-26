package com.porfanid.pomodazzle.sound;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class Sound implements SoundApi {

    private MediaPlayer mediaPlayer;

    @Override
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        assert mediaPlayer != null;
        mediaPlayer = new MediaPlayer(new Media(mediaPlayer.getMedia().getSource()));
        mediaPlayer.play();
        System.out.println("Playing sound");
    }

    @Override
    public void setSound(String sound){
        Media media = new Media(getClass().getResource(sound).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
    }



    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            System.out.println("Stopping sound");
            mediaPlayer.dispose();
        }
    }

    @Override
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}
