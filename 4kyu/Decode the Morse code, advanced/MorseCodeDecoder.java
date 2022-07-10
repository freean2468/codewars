
// https://www.codewars.com/kata/54b72c16cd7f5154e9000457/java
import java.util.regex.*;
import java.util.ArrayList;

public class MorseCodeDecoder {
    public static String decodeBits(String bits) {
        Pattern pattern = Pattern.compile("1+|0+");
        String[] matched = pattern.matcher(bits.replaceAll("^0+|0+$", "")).results()
                .map(MatchResult::group)
                .toArray(String[]::new);

        int unit = Integer.MAX_VALUE;

        for (String seq : matched) {
            unit = seq.length() < unit ? seq.length() : unit;
        }

        return bits.replaceAll("0{" + unit + "}", "0").replaceAll("1{" + unit + "}", "1").replaceAll("0{7}", "   ")
                .replaceAll("1{3}", "-").replaceAll("0{3}", " ").replaceAll("1{1}", ".").replaceAll("0{1}", "");
    }

    public static String decodeMorse(String morseCode) {
        System.out.println(morseCode);
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