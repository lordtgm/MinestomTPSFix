package com.github.lordtgm.minestom;

import com.github.lordtgm.mtf.TimerManager;
import net.minestom.server.extensions.Extension;

public class TpsFixExtension extends Extension {
    TimerManager timerManager;

    public TpsFixExtension() {
        this.timerManager = new TimerManager();
    }

    public void initialize() {
        this.timerManager.setTimerResolution(10);
    }

    public void terminate() {
        this.timerManager.unsetTimerResolution(10);
    }
}

