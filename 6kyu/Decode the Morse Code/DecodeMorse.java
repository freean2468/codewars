
// https://www.codewars.com/kata/54b724efac3d5402db00065e/java
import java.util.*;

public class MorseCodeDecoder {
    public static String decode(String morseCode) {
        String[] manipulated = morseCode.trim().replaceAll("   ", " _ ").split(" ");
        ArrayList<String> decoded = new ArrayList<String>();

        for (String code : manipulated) {
            if (code.equals("_"))
                decoded.add(" ");
            else
                decoded.add(MorseCode.get(code));
        }

        return String.join("", decoded);
    }
}