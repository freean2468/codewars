// https://www.codewars.com/kata/514b92a657cdc65150000006/solutions/java

public class Solution {

  public int solution(int number) {
    int sum = 0;
    
    for (int i = 1; i < number; i += 1) {
      int this_number = number - i;
      
      if (this_number % 3 == 0 || this_number % 5 == 0) {
        sum += this_number;
      }
    }
    
    return sum;
  }
}