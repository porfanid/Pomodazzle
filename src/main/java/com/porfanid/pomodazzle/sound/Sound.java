package com.porfanid.pomodazzle.sound;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class Sound implements SoundApi {

    private MediaPlayer mediaPlayer;
    private Media media;

    @Override
    public void play() {
        // disposing the old media player
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer=null;
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void setSound(String sound){
        media = new Media(getClass().getResource(sound).toExternalForm());
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
