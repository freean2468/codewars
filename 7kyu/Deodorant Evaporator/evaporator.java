// https://www.codewars.com/kata/5506b230a11c0aeab3000c1f/train/java

import java.lang.Math;

public class Evaporator {

    public static int evaporator(double content, double evap_per_day, double threshold) {
        double a = threshold / 100.0;
        double b = 1.0 - evap_per_day / 100.0;
        double c = Math.log(a) / Math.log(b);

        return (int) Math.ceil(c);
    }
}