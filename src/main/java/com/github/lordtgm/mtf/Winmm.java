package com.github.lordtgm.mtf;

import com.sun.jna.Library;

/**
 * An interface to interact with the Winmm.dll Windows library.
 */
public interface Winmm extends Library {
    int timeBeginPeriod(int timerResolution);

    int timeEndPeriod(int timerResolution);
}