package com.github.lordtgm.mtf;

import com.sun.jna.Native;

public class TimerManager {
    Winmm winmm = Native.load("winmm", Winmm.class);

    public TimerManager() {

    }

    public void setTimerResolution(int uPeriod) {
        this.winmm.timeBeginPeriod(uPeriod);
    }

    public void unsetTimerResolution(int uPeriod) {
        this.winmm.timeEndPeriod(uPeriod);
    }


}
