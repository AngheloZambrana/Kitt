package StringMachine;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

  private static final int PRIME = 101;

  public static List<Integer> findPatternMatches(String text, String pattern) {
    List<Integer> matches = new ArrayList<>();
    int n = text.length();
    int m = pattern.length();
    int patternHash = calculateHash(pattern, m);
    int textHash = calculateHash(text, m);

    for (int i = 0; i <= n - m; i++) {
      if (patternHash == textHash && text.substring(i, i + m).equals(pattern)) {
        matches.add(i);
      }
      if (i < n - m) {
        textHash = recalculateHash(text, textHash, i, m);
      }
    }

    return matches;
  }

  private static int calculateHash(String str, int length) {
    int hash = 0;
    for (int i = 0; i < length; i++) {
      hash += str.charAt(i) * Math.pow(PRIME, i);
    }
    return hash;
  }

  private static int recalculateHash(String str, int oldHash, int oldIndex, int patternLength) {
    int newHash = oldHash - str.charAt(oldIndex);
    newHash /= PRIME;
    newHash += str.charAt(oldIndex + patternLength) * Math.pow(PRIME, patternLength - 1);
    return newHash;
  }

  public static void main(String[] args) {
    String text = "abracadabra";
    String pattern = "abr";
    List<Integer> matches = findPatternMatches(text, pattern);
    System.out.println("Pattern matches found at indexes: " + matches);
  }
}
