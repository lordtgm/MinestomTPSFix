package com.github.lordtgm.mtf;

import com.sun.jna.Native;

/**
 * A Windows interrupt timer manager class.
 */
public class TimerManager {
    private Winmm winmm = Native.load("winmm", Winmm.class);

    public TimerManager() {

    }
    /**
     * Sets a minimum timer resolution (maximum delay between interrupts).
     * Run with multipliers of 50 which are less than 16 to achieve 20 tps.
     * @param uPeriod The timer resolution to set.
    */
    public void setTimerResolution(int uPeriod) {
        this.winmm.timeBeginPeriod(uPeriod);
    }

    /**
     * Unsets timer resolution.
     * @param uPeriod The timer resolution to unset. Must be a number which a setTimerResolution is called with before.
     */
    public void unsetTimerResolution(int uPeriod) {
        this.winmm.timeEndPeriod(uPeriod);
    }


}
