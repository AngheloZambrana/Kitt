package KittSegundo;

import java.util.HashMap;
import java.util.LinkedList;

public class TextComparator {
  private final LinkedList<String> similarValues;
  private final HashMap<String, String> errors;
  private int sizeOfPrincipalSentence;

  public TextComparator() {
    similarValues = new LinkedList<>();
    errors = new HashMap<>();
  }

  public ComparisonResult calculateSimilarity(String file1, String file2) {
    String[] sentencesInFile1 = file1.split("\\.\\s*");
    String[] sentencesInFile2 = file2.split("\\.\\s*");

    LinkedList<Integer> listOfQuantityOfSimilarities = new LinkedList<>();

    if (sentencesInFile1.length != sentencesInFile2.length) {
      for (String sentence1 : sentencesInFile1) {
        for (String sentence2 : sentencesInFile2)
          listOfQuantityOfSimilarities.add(compareSentences(sentence1, sentence2));
      }
    } else listOfQuantityOfSimilarities.add(compareSentences(file1, file2));

    int getLCS = listOfQuantityOfSimilarities.stream().mapToInt(Integer::intValue).max().orElse(0);
    double similarityBetweenTwoFiles = getLCS * (100.0 / sizeOfPrincipalSentence);
    return new ComparisonResult(getLCS, similarityBetweenTwoFiles, errors);
  }

  public int compareSentences(String text1, String text2) {
    String[] words1 = text1.split("[\\s.]+");
    String[] words2 = text2.split("[\\s.]+");
    sizeOfPrincipalSentence = words2.length;

    similarValues.clear();
    return calculateLCS(words1, words2);
  }

  public void compareWords(String word1, String word2, String[] words, int j) {
    if (word1.equalsIgnoreCase(word2) && !similarValues.contains(word1.toLowerCase()))
      similarValues.add(word1.toLowerCase());
    else
      compareSimilarWords(word1, word2, words, j);
  }

  public void compareSimilarWords(String word1, String word2, String[] words, int j) {
    if (word1.length() > 3 && word2.length() > 3 && firstThreeLettersMatch(word1, word2)) {
      int distance = calculateLevenshteinDistance(word1.toLowerCase(), word2.toLowerCase());
      if (distance != 0 && distance < 3) {
        similarValues.add(word2);
        errors.put(word2, word1);
        words[j - 1] = word1;
      }
    }
  }

  private int calculateLCS(String[] words1, String[] words2) {
    int[][] dp = new int[words1.length + 1][words2.length + 1];
    for (int i = 1; i <= words1.length; i++) {
      for (int j = 1; j <= words2.length; j++) {
        compareWords(words1[i - 1], words2[j - 1], words2, j);

        if (words1[i - 1].equalsIgnoreCase(words2[j - 1]))
          dp[i][j] = dp[i - 1][j - 1] + 1;
        else
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
    return dp[words1.length][words2.length];
  }

  private int calculateLevenshteinDistance(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
    for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
        dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
      }
    }
    return dp[s1.length()][s2.length()];
  }

  private boolean firstThreeLettersMatch(String word1, String word2) {
    return word1.regionMatches(true, 0, word2, 0, 3);
  }
}
