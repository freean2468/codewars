// https://www.codewars.com/kata/5541f58a944b85ce6d00006a/java

import java.util.ArrayList; // import the ArrayList class

public class ProdFib { // must be public for codewars 
  public static ArrayList<Long> fibonacciTable = new ArrayList<Long>();
    
  static {
    fibonacciTable.add(0L);
    fibonacciTable.add(1L);
    
    for (int i = 2; i < 100; i += 1) {
      fibonacciTable.add(fibonacciTable.get(i - 2) + fibonacciTable.get(i - 1));
    }
  }
  
  public static long[] productFib(long prod) {
    System.out.println(ProdFib.fibonacciTable);
    
    for (int i = 0; true; i += 1) {
      long left = ProdFib.fibonacciTable.get(i);
      long right = ProdFib.fibonacciTable.get(i+1);
      long this_prod = left * right;
      
      if (this_prod == prod) {
        return new long[] {left, right, 1};
      } else if (this_prod < prod) {
        continue;
      } else {
        return new long[] {left, right, 0};
      }
    }
   }
}