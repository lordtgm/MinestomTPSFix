import com.github.lordtgm.mtf.TimerManager;

import com.github.lordtgm.minestom.TpsFixExtension;

public class Test {
    static long getTps() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(50);
        }
        return 1000 / ((System.currentTimeMillis() - startTime) / 100);
    }
    static void testTps() throws InterruptedException {
        TimerManager timerManager = new TimerManager();
        System.out.println(getTps());
        timerManager.setTimerResolution(10);
        System.out.println(getTps());
        timerManager.unsetTimerResolution(10);
    }

    static void testPlatform() {
        System.out.println(System.getProperty("os.name"));
        System.out.println(TpsFixExtension.isWindows());
    }

    public static void main(String[] args) {

        try {
            testTps();
        } catch (InterruptedException e) {
            System.out.println("Tps check failed: Interrupted");
        }

        testPlatform();

    }

}
