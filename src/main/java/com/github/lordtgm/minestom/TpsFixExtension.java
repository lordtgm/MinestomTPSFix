package com.github.lordtgm.minestom;

import com.github.lordtgm.mtf.TimerManager;
import net.minestom.server.extensions.Extension;

/**
 * A Minestom extension class which handles the timer resolution.
 * Safe to use with all operating systems.
 */
public class TpsFixExtension extends Extension {
    private final TimerManager timerManager;
    private final int resolution;

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
        if (!isWindows()) {
            this.timerManager = null;
            this.resolution = 0;
        } else {
            this.timerManager = new TimerManager();
            this.resolution = resolution;
        }
    }

    /**
     * Initializes the extension and sets the timer resolution.
     * Called by Minestom.
     */
    public void initialize() {
        if (!isWindows()) return;
        this.timerManager.setTimerResolution(resolution);
    }
    /**
     * Terminates the extension and unsets the timer resolution.
     * Called by Minestom.
     */
    public void terminate() {
        if (!isWindows()) return;
        this.timerManager.unsetTimerResolution(resolution);
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}

