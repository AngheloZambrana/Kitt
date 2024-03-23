package KittSegundo;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ComparisonResult {
  private final double similarityPercent;
  private final int qSequence;
  private final HashMap<String, String> misspellings;

  public ComparisonResult(int qSequence, double similarityPercent, HashMap<String, String> misspellings) {
    this.qSequence = qSequence;
    this.misspellings = misspellings;
    this.similarityPercent = similarityPercent;
  }

  private String getMisspellings() {
    StringBuilder result = new StringBuilder();
    result.append("[");
    int count = 0;
    for (Map.Entry<String, String> entry : misspellings.entrySet()) {
      if (count < qSequence) {
        result.append(entry.getKey()).append(" - ").append(entry.getValue());
        if (++count < misspellings.size() && count < qSequence) result.append(", ");
      }
    } return result.append("]").toString();
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");

    return "ComparisonResult = " +
            "\n\t\t - Similarity (%) = " + df.format(similarityPercent) + "%" +
            "\n\t\t - Misspellings = " + getMisspellings();
  }
}
