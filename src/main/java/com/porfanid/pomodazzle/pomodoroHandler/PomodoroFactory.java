package com.porfanid.pomodazzle.pomodoroHandler;

public class PomodoroFactory {
    public static PomodoroInt createPomodoro(PomodoroTimerListener listener) {
        return new PomodoroTimer(listener, PomodoroDefaultValues.INSTANCE);
    }
}
