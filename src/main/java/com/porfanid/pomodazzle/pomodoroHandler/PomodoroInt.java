package com.porfanid.pomodazzle.pomodoroHandler;

import java.io.Closeable;

public interface PomodoroInt extends Closeable, AutoCloseable {
    void start();
    void changeState();
    void reset();
    int getRemainingTime();
    double getProgress();
    boolean isWorking();
    boolean isOnBreak();
    void setSound(String sound);
    void deleteSound();
}
