package KittSegundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    // Rutas de los archivos de texto
    String filePath1 = "/home/fundacion/Documents/algoritmiaII/ultimo/Kitt/app/src/main/resources/archivosDP/archivo1.txt";
    String filePath2 = "/home/fundacion/Documents/algoritmiaII/ultimo/Kitt/app/src/main/resources/archivosDP/archivo2.txt";

    // Lee el contenido de los archivos de texto
    String text1 = readTextFromFile(filePath1);
    String text2 = readTextFromFile(filePath2);

    if (text1 != null && text2 != null) {
      TextComparator textComparator = new TextComparator();

      ComparisonResult comparisonResult = textComparator.calculateSimilarity(text1, text2);
      System.out.println(comparisonResult.toString());
    }
  }

  private static String readTextFromFile(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        contentBuilder.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return contentBuilder.toString();
  }
}
