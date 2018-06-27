package si.fri.math.geometrija;

public class Kvadrat {
  // stranica kvadrata
  static int a = 5;

  static void izpis() {
    System.out.printf("Ploscina kvadrata s stranico %d je %d\n",a, a*a);
  }
  
  public static void main(String[] args) {
    izpis();
  }
}
