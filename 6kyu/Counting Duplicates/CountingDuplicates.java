// https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1/java
import java.util.*;

public class CountingDuplicates {
  public static int duplicateCount(String text) {
    text = text.toLowerCase();
    Set<Character> duplicates = new HashSet<Character>();
    
    for(int i = 0; i < text.length(); i += 1) {
      if (text.indexOf(text.charAt(i)) != i) {
        duplicates.add(text.charAt(i));
      }
    }
    
    return duplicates.size();
  }
}