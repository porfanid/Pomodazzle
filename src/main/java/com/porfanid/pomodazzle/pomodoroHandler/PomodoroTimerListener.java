package com.porfanid.pomodazzle.pomodoroHandler;

public interface PomodoroTimerListener {
    void onTimeUpdate(int minutesLeft, int secondsLeft);
    void onStateChange(TimerState state);
}