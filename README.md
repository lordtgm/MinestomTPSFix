# MinestomTPSFix
this extension is a fix to low tps on Windows machines.

## Table of Contents

[Usage](#usage)

[How does this work](#how-does-this-work)

[TODO](#todo)

## Usage
just build the jar file and throw it into extensions folder in your server root folder.
this is only meant for Windows systems and shouldn't be used on Linux machines, most likely will throw errors.

## How does this work
as we know, Minecraft runs at a max rate of 20 ticks per second, or one tick at least every 50 ms. ticks might take longer, but in the optimal scenario it should be so. if tick process completes before 50 ms (which is so likely to happen), Minestom will sleep for the rest of 50 ms, so it doesn't go over 20 tps.

this is where the problem begins. when a thread is asleep, there is no process on it to check for if it's the time for the thread to awaken and continue. so wake-ups (or interrupts) are done by a separate part in the processor. so when you call sleep in your thread, it won't wake up exactly at the time specified, but can happen at any time between the specified time and specified time + interrupt delay (16 ms).

on Windows, they happen at a rate of 64 interrupts each second, or once each 16 ms by default. but Minecraft ticks are 50 ms long. this means that ticks won't take 50 ms, but 64 ms approximately. because if the last time the thread was awoken (when the tick started) was x ms, it takes some time to process the tick and then wait for the rest of 50 ms. but interrupts occur at x+16, x+32, x+48 and x+64. so the tick will take 64 ms, which results in a roughly 16 tps instead of 20.

however, timer resolution (interrupt delay basically) can be changed using a method in `winmm.dll` file called `timeBeginPeriod`. this method takes an int, and sets is as minimum timer resolution (it actually might be less, if other apps request lower times). this extension works by calling the method on initiate and setting timer resolution to 10 ms (100 interrupts each sec).

there are two important notes, one is that your tps might be a little less than 20 even with this extension enabled. this might be caused by another application setting timer resolution to a lower number like 8 which isn't a multiplier of 20. don't sure if it can happen or not, but if you encountered it you can change the 10ms in code to 1ms.
the second one is increasing timer resolution might result in higher power usage. 100 interrupts instead of 64 per sec isn't a huge deal, but if you set it to 1ms it becomes 1000 times per sec with is actually a lot. so be careful with it.

## TODO

disable the extension if the os is Linux, so you don't need to remove the extension
