package StringMachine;

import java.util.HashMap;
import java.util.Map;

public class LongestPrefixMatch {

  public static String findLongestPrefixMatch(String s) {
    Map<String, Integer> prefixCounts = new HashMap<>();
    String longestPrefix = "";
    int maxCount = 0;

    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        String prefix = s.substring(i, j);
        prefixCounts.put(prefix, prefixCounts.getOrDefault(prefix, 0) + 1);
        if (prefixCounts.get(prefix) > maxCount ||
                (prefixCounts.get(prefix) == maxCount && prefix.length() > longestPrefix.length())) {
          maxCount = prefixCounts.get(prefix);
          longestPrefix = prefix;
        }
      }
    }

    return longestPrefix + " - " + maxCount;
  }

  public static void main(String[] args) {
    String s = "abababc";
    String result = findLongestPrefixMatch(s);
    System.out.println("Longest prefix match: " + result);
  }
}
