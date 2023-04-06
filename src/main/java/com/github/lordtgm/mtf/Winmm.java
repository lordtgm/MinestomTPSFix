package com.github.lordtgm.mtf;

import com.sun.jna.Library;

public interface Winmm extends Library {
    int timeBeginPeriod(int var1);

    int timeEndPeriod(int var1);
}