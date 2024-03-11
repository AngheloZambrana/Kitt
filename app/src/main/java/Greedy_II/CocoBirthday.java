package Greedy_II;

import java.util.*;

class CocoBirthday {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] happiness = new int[n];
    for (int i = 0; i < n; i++) {
      happiness[i] = sc.nextInt();
    }
    System.out.println(maxGuests(happiness));
  }

  public static int maxGuests(int[] happiness) {
    Arrays.sort(happiness); // Greedy choice: Sort happiness in ascending order
    int maxGuests = 0;
    int timeToEnter = 1; // Tiempo para que el siguiente invitado entre
    for (int i = 0; i < happiness.length; i++) {
      if (happiness[i] >= timeToEnter) {
        maxGuests++; // El invitado puede entrar
        timeToEnter++; // Incrementa el tiempo para el siguiente invitado
      }
    }
    return maxGuests;
  }
}
