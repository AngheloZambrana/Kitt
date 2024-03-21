package Audies_party;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AudieParty {

  public static class Villager {
    String name;
    public Map<String, Integer> relationships;

    public Villager(String name) {
      this.name = name;
      this.relationships = new HashMap<>();
    }
  }

  public static List<String> generateGuestList(Map<String, Villager> villagers, int x) {
    List<String> guestList = new ArrayList<>();
    Set<String> visited = new HashSet<>();

    for (Villager villager : villagers.values()) {
      if (!visited.contains(villager.name)) {
        guestList.add(villager.name);
        visited.add(villager.name);
        for (String neighbor : villager.relationships.keySet()) {
          if (villager.relationships.get(neighbor) >= x && !visited.contains(neighbor)) {
            guestList.add(neighbor);
            visited.add(neighbor);
          }
        }
      }
    }

    return guestList;
  }

  public static Map<Integer, List<String>> divideIntoGroups(List<String> guestList, int k) {
    Map<Integer, List<String>> groups = new HashMap<>();
    Collections.sort(guestList, (a, b) -> {
      int sumA = 0, sumB = 0;
      for (String neighbor : groups.getOrDefault(a.hashCode(), Collections.emptyList())) {
        sumA += neighbor.hashCode();
      }
      for (String neighbor : groups.getOrDefault(b.hashCode(), Collections.emptyList())) {
        sumB += neighbor.hashCode();
      }
      return Integer.compare(sumB, sumA);
    });

    for (int i = 0; i < k; i++) {
      groups.put(i, new ArrayList<>());
    }

    for (String guest : guestList) {
      int minGroupSize = Integer.MAX_VALUE;
      int minGroup = -1;

      for (int i = 0; i < k; i++) {
        int groupSize = groups.get(i).size();
        if (groupSize < minGroupSize) {
          minGroupSize = groupSize;
          minGroup = i;
        }
      }

      groups.get(minGroup).add(guest);
    }

    return groups;
  }

  public static void main(String[] args) {
    String fileName = "app/src/main/resources/archivos/archivo.txt"; // Nombre del archivo que contiene la información de los aldeanos
    int x = 7; // Valor mínimo de relación para invitar a un aldeano
    int k = 2; // Número de grupos en los que dividir a los invitados

    Map<String, Villager> villagers = new HashMap<>();

    try {
      Scanner scanner = new Scanner(new File(fileName));

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
          String[] parts = line.split("\\s+");
          if (parts.length >= 3) {
            String villager1 = parts[0];
            String villager2 = parts[1];
            int relationship = Integer.parseInt(parts[2]);

            villagers.putIfAbsent(villager1, new Villager(villager1));
            villagers.putIfAbsent(villager2, new Villager(villager2));

            villagers.get(villager1).relationships.put(villager2, relationship);
            villagers.get(villager2).relationships.put(villager1, relationship);
          } else {
            System.err.println("Invalid line format: " + line);
          }
        }
      }

      List<String> guestList = generateGuestList(villagers, x);
      System.out.println("Guest List:");
      System.out.println(guestList);

      Map<Integer, List<String>> groups = divideIntoGroups(guestList, k);
      System.out.println("\nGroups:");
      for (int i = 0; i < k; i++) {
        System.out.println("Group " + (i + 1) + ": " + groups.get(i));
      }

    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + fileName);
      e.printStackTrace();
    }
  }
}
