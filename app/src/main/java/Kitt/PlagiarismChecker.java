package Kitt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlagiarismChecker {
  public static void main(String[] args) {
    String text1 = readTextFromFile("Kit/src/main/java/resources/archivos/archivo.txt");
    String text2 = readTextFromFile("Kit/src/main/java/resources/archivos/README.txt");

    double similarityPercentage = calculateSimilarityPercentage(text1, text2);
    System.out.println("Similarity percentage: " + similarityPercentage + "%");

    String misspelledWords = identifyMisspelledWords(text1, text2);
    System.out.println("Misspelled words: " + misspelledWords);
  }

  public static String readTextFromFile(String fileName) {
    StringBuilder text = new StringBuilder();
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        text.append(scanner.nextLine()).append("\n");
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + fileName);
      e.printStackTrace();
    }
    return text.toString();
  }

  public static double calculateSimilarityPercentage(String text1, String text2) {
    int[][] dp = new int[text1.length() + 1][text2.length() + 1];

    for (int i = 0; i <= text1.length(); i++) {
      for (int j = 0; j <= text2.length(); j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int lcsLength = dp[text1.length()][text2.length()];
    double similarityPercentage = (double) lcsLength / Math.max(text1.length(), text2.length()) * 100.0;
    return similarityPercentage;
  }


  public static String identifyMisspelledWords(String text1, String text2) {
    StringBuilder misspelledWords = new StringBuilder();

    // Dividir los textos en palabras
    String[] words1 = text1.split("\\s+");
    String[] words2 = text2.split("\\s+");

    // Comparar cada palabra del segundo texto con las del primero
    for (String word2 : words2) {
      boolean foundMatch = false;
      for (String word1 : words1) {
        double similarityPercentage = calculateSimilarityPercentage(word1, word2);
        if (similarityPercentage >= 55.0) { // Umbral de similitud del 55%
          foundMatch = true;
          break;
        }
      }
      if (!foundMatch) {
        misspelledWords.append(word2).append(" - "); // Agregar palabra mal escrita
      }
    }

    return misspelledWords.toString().trim(); // Devolver las palabras mal escritas
  }
}
