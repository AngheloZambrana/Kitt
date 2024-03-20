package StringMachine;

public class LongestSuffixPrefixMatch {

  public static void findLongestSuffixPrefixMatch(String text, String pattern, int[] lengths) {
    for (int i = 0; i < lengths.length; i++) {
      String subText = text.substring(0, lengths[i]);
      int longestMatch = 0;
      for (int j = 0; j < subText.length(); j++) {
        if (pattern.startsWith(subText.substring(j))) {
          longestMatch = subText.length() - j;
          break;
        }
      }
      System.out.println(longestMatch);
    }
  }

  public static void main(String[] args) {
    String text = "abba";
    String pattern = "bab";
    int[] lengths = {4, 1, 2, 3, 4};
    findLongestSuffixPrefixMatch(text, pattern, lengths);
  }
}

