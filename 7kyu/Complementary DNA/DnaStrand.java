// https://www.codewars.com/kata/554e4a2f232cdd87d9000038/java

public class DnaStrand {
    public static String makeComplement(String dna) {
      char[] chars = dna.toCharArray();
      String complements = "";
  
      for (char ch: chars) {
          switch(ch){
              case 'A':
                complements += 'T';
                break;
              case 'T':
                complements += 'A';
                break;
              case 'C':
                complements += 'G';
                break;
              case 'G':
                complements += 'C';
                break;
          }
      }
      
      return complements;
    }
  }