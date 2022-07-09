// https://www.codewars.com/kata/52c31f8e6605bcc646000082/java
public class Solution {
    public static int[] twoSum(int[] numbers, int target) {
      for (int i = 0; i < numbers.length; i += 1) {
        for (int j = i + 1; j < numbers.length; j += 1) {
          if (numbers[i] + numbers[j] == target) {
            return new int[]{i, j};
          }
        }  
      }
      
      new Exception("Shoudln't be here");
      return new int[2];
    }
}