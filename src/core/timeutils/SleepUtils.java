package timeutils;

public class SleepUtils {

    public static void sleep(int timeInSecs) {
        try {
            Thread.sleep(timeInSecs * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
