package Greedy_II;

import java.util.Arrays;
import java.util.Scanner;

public class CocoThemBirthday {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] happiness = new int[n];

    // Leer los niveles de felicidad de cada invitado
    for (int i = 0; i < n; i++) {
      happiness[i] = scanner.nextInt();
    }

    // Ordenar los niveles de felicidad
    Arrays.sort(happiness);

    // Calcular el disfrute máximo
    int maxEnjoyment = 0;

    // Sumar las diferencias entre niveles de felicidad adyacentes, excepto el primer y último invitado
    for (int i = 1; i < n - 1; i++) {
      maxEnjoyment += happiness[i + 1] - happiness[i];
      i++; // Salta al siguiente par de invitados
    }

    // Agregar la diferencia entre el primer y último invitado
    maxEnjoyment += happiness[n - 1] - happiness[0];

    // Imprimir el disfrute máximo
    System.out.println(maxEnjoyment);
  }
}
