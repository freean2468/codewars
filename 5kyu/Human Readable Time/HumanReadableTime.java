// https://www.codewars.com/kata/52685f7382004e774f0001f7/java
public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        int s = seconds % 60;
        int m = seconds / 60 % 60;
        int h = seconds / (60 * 60);

        return String.format("%02d:%02d:%02d", h, m, s);
    }
}