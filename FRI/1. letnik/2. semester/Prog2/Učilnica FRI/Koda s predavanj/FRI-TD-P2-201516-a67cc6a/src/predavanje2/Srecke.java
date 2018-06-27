// Izpis cenika za prodajo sreck. Vsaka srecka stane 1.25 EUR

public class Srecke {

  public static void main(String args[]) {
    System.out.println("Stevilo sreck | Cena (EUR)");

    // tako deklarirana spremenljivka i je vidna v 
    // celotni kodi metoda main od te vrstice naprej.
    int i;
    for (i = 0; i < 26; i = i + 1) {
      System.out.print("-");
    }
    System.out.println();
    // Spodnji ukaz je OK, saj je i "ziva" tudi izven zanke
    //System.out.println(i);

    // tako deklarirana spremenljivka j "zivi" samo znotraj te zanke
    for (int j = 1; j <= 10; j = j + 1) {

      System.out.printf(
           "   %02d%10s    %5.2f \n", j, "|", j * 1.25);
    }
    // Spodnji ukaz ni OK, saj j zunaj zanke ne obstaja
    //System.out.println(j);
  }
}
