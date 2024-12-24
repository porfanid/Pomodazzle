package com.porfanid.pomodazzle.pomodoroHandler;
import com.porfanid.pomodazzle.sound.SoundApi;
import com.porfanid.pomodazzle.sound.SoundFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer implements PomodoroInt {


    private final int workTime;
    private final int breakTime;
    private final int longBreakTime;
    private int currentTime; // Current time in seconds for countdown
    private int pomodorosCompleted;
    private final Timer timer;
    private TimerTask currentTask;
    private TimerState state;
    private final PomodoroTimerListener listener;
    private SoundApi soundPlayer;

    private int isBreakTime; // New flag to track if we are in a break phase

    public PomodoroTimer(PomodoroTimerListener listener, PomodoroDefaultValues defaultValues) {

        this.workTime = defaultValues.getDefaultWorkTime()*60;
        this.breakTime = defaultValues.getDefaultBreakTime()*60;
        this.longBreakTime = defaultValues.getDefaultLongBreakTime()*60;
        this.currentTime = workTime;
        this.pomodorosCompleted = 0;
        this.state = TimerState.STOPPED;
        this.timer = new Timer();
        this.listener = listener;
        this.isBreakTime = 0; // Initially, it's not break time
        this.soundPlayer = SoundFactory.createSound();
    }

    // Start the Pomodoro timer
    public void start() {
        if (state == TimerState.RUNNING) {
            return; // Do nothing if the timer is already running
        }
        state = TimerState.RUNNING;
        notifyStateChange();
        scheduleTimerTask();
    }

    // Pause the Pomodoro timer
    public void pause() {
        if (state == TimerState.RUNNING) {
            state = TimerState.PAUSED;
            currentTask.cancel();
            notifyStateChange();
        }
    }

    // Reset the Pomodoro timer
    public void reset() {
        if (state == TimerState.RUNNING || state == TimerState.PAUSED) {
            currentTask.cancel();
        }
        currentTime = workTime;
        pomodorosCompleted = 0;
        state = TimerState.STOPPED;
        isBreakTime = 0; // Reset the break flag
        notifyStateChange();
    }

    // Update the current time
    private void updateTime() {
        if (currentTime > 0) {
            currentTime--;
            if(currentTime<4){
                soundPlayer.play();
            }
            notifyTimeUpdate();
        } else {
            onTimeUp();
        }
    }

    // Handle time-up event (switch to break)
    private void onTimeUp() {
        currentTask.cancel(); // Cancel the current task

        if (isBreakTime==0) {
            // Switch to break time
            pomodorosCompleted++;

            if (pomodorosCompleted % 4 == 0) {
                currentTime = longBreakTime; // Long break after every 4 Pomodoros
                isBreakTime=2;
            } else {
                currentTime = breakTime; // Regular break
                isBreakTime=1;
            }
        } else {
            // Switch to work time
            currentTime = workTime;
            isBreakTime=0;
        }

        notifyStateChange(); // Notify the listener of state changes
        notifyTimeUpdate(); // Ensure the time is updated in the UI

        scheduleTimerTask(); // Start the next phase (work or break)
    }


    public void changeState() {
        if(state==TimerState.RUNNING) {
            pause();
        } else {
            start();
        }
        notifyStateChange();
    }

    private void scheduleTimerTask() {
        currentTask = new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        };
        timer.scheduleAtFixedRate(currentTask, 0, 1000); // Runs every second
    }

    // Notify the listener about time updates
    private void notifyTimeUpdate() {
        int minutesLeft = currentTime / 60;
        int secondsLeft = currentTime % 60;
        listener.onTimeUpdate(minutesLeft, secondsLeft);
    }

    // Notify the listener about state change
    private void notifyStateChange() {
        listener.onStateChange(state);
    }



    // Get the current remaining time in seconds
    public int getRemainingTime() {
        return currentTime;
    }

    // New method to check if currently on a break
            public boolean isOnBreak() {
        return isBreakTime>0;
    }

    // New method to check if currently working
    public boolean isWorking() {
        return isBreakTime==0;
    }

    public double getProgress() {
        double totalTime;
        if (isBreakTime==0) {
            totalTime = workTime;
        } else if (isBreakTime==1) {
            totalTime = breakTime;
        } else {
            totalTime = longBreakTime;
        }
        return currentTime / totalTime;
    }

    @Override
    public void close() throws IOException {
        if (currentTask != null) {
            currentTask.cancel();
        }
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    @Override
    public void setSound(String sound) {
        if(soundPlayer==null){
            this.soundPlayer = SoundFactory.createSound();
        }
        this.soundPlayer.setSound(sound);
        this.soundPlayer.setVolume(1.0);
    }

    @Override
    public void deleteSound() {
        this.soundPlayer = null;
    }
}