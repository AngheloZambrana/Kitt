package Kitt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlagiarismCheckerTest {

  @Test
  void testReadTextFromFile_FileExists() {
    String fileName = "resources/archivo.txt";
    String expectedContent = "This is a test text.";

    String actualContent = PlagiarismChecker.readTextFromFile(fileName);

    assertEquals(expectedContent, actualContent);
  }

  @Test
  void testCalculateSimilarityPercentage_IdenticalTexts() {
    String text1 = "This is a test text.";
    String text2 = "This is a test text.";

    double expectedSimilarity = 100.0;
    double actualSimilarity = PlagiarismChecker.calculateSimilarityPercentage(text1, text2);

    assertEquals(expectedSimilarity, actualSimilarity, 0.01);
  }

  @Test
  void testIdentifyMisspelledWords() {
    String text1 = "This is a test text.";
    String text2 = "This is a tst text.";

    String expectedMisspelledWords = "tst - "; // No se esperan palabras correctamente escritas en este caso
    String actualMisspelledWords = PlagiarismChecker.identifyMisspelledWords(text1, text2);

    assertEquals(expectedMisspelledWords, actualMisspelledWords);
  }


}
