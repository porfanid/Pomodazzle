package com.porfanid.pomodazzle.sound;

public interface SoundApi {
    void play();
    void stop();
    void setVolume(double volume);
    void setSound(String sound);
}
