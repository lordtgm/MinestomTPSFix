package com.github.lordtgm.minestom;

import com.github.lordtgm.mtf.TimerManager;
import net.minestom.server.extensions.Extension;

/**
 * A Minestom extension class which handles the timer resolution
 */
public class TpsFixExtension extends Extension {
    private TimerManager timerManager;
    private int resolution;

    /**
     * Constructor used by Minestom.
     */
    public TpsFixExtension() {
        this(10);
    }

    /**
     * Constructs an extension object. Use if you want to include this in your server jar.
     * @param resolution Custom resolution to set. Default to 10.
     */
    public TpsFixExtension(int resolution) {

        this.timerManager = new TimerManager();
        this.resolution = resolution;

    }

    /**
     * Initializes the extension and sets the timer resolution.
     * Called by Minestom.
     */
    public void initialize() {
        this.timerManager.setTimerResolution(10);
    }
    /**
     * Terminates the extension and unsets the timer resolution.
     * Called by Minestom.
     */
    public void terminate() {
        this.timerManager.unsetTimerResolution(10);
    }
}

