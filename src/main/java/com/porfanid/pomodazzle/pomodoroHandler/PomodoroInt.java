package com.porfanid.pomodazzle.pomodoroHandler;

import java.io.Closeable;

public interface PomodoroInt extends Closeable, AutoCloseable {
    public void start();
    public void changeState();
    public void reset();
    public int getRemainingTime();
    public double getProgress();
    public boolean isWorking();
    public boolean isOnBreak();
}
