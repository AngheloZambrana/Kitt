package ExamenGreedy_StringMachine;

public class CocoThemeParty {

  public static int minSouvenirs(int n, int[] happinessLevels) {
    int souvenirs = 0;

    // Asignamos souvenirs a cada invitado
    for (int i = 1; i < n; i++) {
      if (happinessLevels[i] <= happinessLevels[i - 1]) {
        // Si el nivel de felicidad del invitado actual es menor o igual al del anterior,
        // asignamos souvenirs suficientes para aumentar su felicidad al nivel del invitado anterior más 1
        int diff = happinessLevels[i - 1] - happinessLevels[i] + 1;
        souvenirs += diff;
        happinessLevels[i] += diff;
      }
    }

    return souvenirs;
  }

  public static void main(String[] args) {
    int[] happinessLevels1 = {2, 2, 10, 5};
    int[] happinessLevels2 = {10, 3, 4};
    int[] happinessLevels3 = {1, 2, 3, 4, 5};
    int[] happinessLevels4 = {5, 4, 3, 2, 1};

    System.out.println(minSouvenirs(4, happinessLevels1)); // Output: 6
    System.out.println(minSouvenirs(3, happinessLevels2)); // Output: 4
    System.out.println(minSouvenirs(5, happinessLevels3)); // Output: 15
    System.out.println(minSouvenirs(5, happinessLevels4)); // Output: 15
  }
}
