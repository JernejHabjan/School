// Izpis cenika za prodajo sreck. Vsaka srecka stane 1.25 EUR
// Obseg tabele (od kje do kje naj se izpise), doloci uporabnik
// preko argumentov

public class SreckePlus {

  public static void main(String args[]) {
    int z = 1;
    int k = 10;

    if (args.length == 2) {
      z = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    }

    System.out.println("Stevilo sreck | Cena (EUR)");

    int i;
    for (i = 0; i < 26; i = i + 1) {
      System.out.print("-");
    }
    System.out.println();

    for (int j = z; j <= k; j = j + 1) {
      System.out.printf(
        "   %02d%10s    %5.2f \n", j, "|", j * 1.25);
    }
  }
}
