# MinestomTPSFix
This extension fixes low tps on Windows machines.

## Table of Contents

[Usage](#usage)

[How it works](#how-it-works)

[TODO](#todo)

## Usage
Just build the .jar file or [download from releases](https://github.com/lordtgm/MinestomTPSFix/releases) and throw it into the extensions folder in your server root folder.


## How it works
Minecraft runs at a max rate of 20 ticks per second, or one tick every 50 ms (1000÷20=50). (Ticks will take longer if the server is overloaded). If the tick takes less than 50 ms to process (which is very likely), Minestom will sleep for the rest of the 50 ms, so it doesn't go over 20 tps.

When a thread is asleep, there is no process on it to check for when it's time for the thread to awaken and continue, therefore wake-ups (or interrupts) are done by a separate part of the processor. When you call sleep in your thread, it won't wake up exactly at the time specified, and instead can happen at any time between the specified time and specified time + interrupt delay (16 ms).

On Windows, they happen at a rate of 64 interrupts each second, or once every 16 ms (1000÷64=15.625) by default, however Minecraft ticks are 50 ms long. This means that ticks won't take 50 ms, but ~64 ms (smallest multiple of 15.625 above 50 = 62.5) instead. This is because if the last time the thread was awoken (when the tick started) was x ms, it takes some time to process the tick and then wait for the rest of 50 ms. Since interrupts occur at x+16, x+32, x+48 and x+64, the tick will take 64 ms, which results in roughly 16 tps instead of 20 tps.

To fix this, however, timer resolution (interrupt delay) can be changed using a method in `winmm.dll` called `timeBeginPeriod`. This method takes an int, and sets the minimum timer resolution (which can be less if other apps request lower times). This extension works by calling the method on initiate and setting timer resolution to 10 ms (100 interrupts each sec).

There are two important notes, one is that your tps might be a little less than 20 even with this extension enabled. This might be caused by another application setting timer resolution to a lower number like 8 which isn't a multiple of 20. Not sure if it can happen, but if you are encountering problems you can change it to 1ms.
The second one is increasing timer resolution might result in higher power usage. 100 interrupts instead of 64 per sec isn't a huge deal, but if you set it to 1 ms it becomes 1000 times per second which is quite a lot, so be careful.

## TODO


